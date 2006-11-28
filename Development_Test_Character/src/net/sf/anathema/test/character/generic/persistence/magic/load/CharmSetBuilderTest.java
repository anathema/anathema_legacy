package net.sf.anathema.test.character.generic.persistence.magic.load;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.PermanentCostList;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmSetBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

public class CharmSetBuilderTest extends BasicTestCase {

  private CharmSetBuilder builder;
  private Document document;
  private Element charmElement;
  private final IExaltedRuleSet rules = ExaltedRuleSet.CoreRules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.builder = new CharmSetBuilder();
    this.document = DocumentFactory.getInstance().createDocument(new DefaultElement("Root")); //$NON-NLS-1$
    this.charmElement = CharmXmlTestUtils.createCharmElement("Dummy"); //$NON-NLS-1$
    document.getRootElement().add(charmElement);
  }

  public void testCharmIsRead() throws Exception {
    ICharm[] charms = builder.buildCharms(document, new ArrayList<ICharm>(), rules);
    assertEquals(1, charms.length);
  }

  public void testCharmIsReplaced() throws Exception {
    List<ICharm> list = new ArrayList<ICharm>();
    list.add(getTestCharm("Dummy", CharmType.ExtraAction, null)); //$NON-NLS-1$
    ICharm[] charms = builder.buildCharms(document, list, rules);
    assertEquals(1, charms.length);
    assertEquals(CharmType.Simple, charms[0].getCharmTypeModel().getCharmType());
  }

  public void testNewCharmsAreConnected() throws Exception {
    List<ICharm> list = new ArrayList<ICharm>();
    final String expectedParentId = "ParentDummy"; //$NON-NLS-1$
    Charm parentDummy = getTestCharm(expectedParentId, CharmType.ExtraAction, null);
    list.add(parentDummy);
    addParentReference(charmElement, expectedParentId);
    ICharm[] charms = builder.buildCharms(document, list, rules);
    assertEquals(2, charms.length);
    assertTrue(connectionExists(charms[0], charms[1]));
  }

  private boolean connectionExists(ICharm charm1, ICharm charm2) {
    if (!charm1.getParentCharms().isEmpty()) {
      if (!(charm1.getParentCharms().toArray()[0] == charm2)) {
        return false;
      }
      if (charm2.isTreeRoot()) {
        return true;
      }
    }
    else if (!charm2.getParentCharms().isEmpty()) {
      if (!(charm2.getParentCharms().toArray()[0] == charm1)) {
        return false;
      }
      if (charm1.isTreeRoot()) {
        return true;
      }
    }
    return false;
  }

  private void addParentReference(Element element, String parentId) {
    Element prerequisiteElement = element.element("prerequisite"); //$NON-NLS-1$
    Element referenceElement = prerequisiteElement.addElement("charmReference"); //$NON-NLS-1$
    referenceElement.addAttribute("id", parentId); //$NON-NLS-1$
  }

  public void testExistingLinksUnchanged() throws Exception {
    // An existing charms parent is replaced, but the connection is not changed on the original element!
    // A clone of the existing element must refer to the new Element as it's parent.
    String expectedParentId = "ParentId"; //$NON-NLS-1$
    addParentReference(charmElement, expectedParentId);
    final Element oldParentElement = CharmXmlTestUtils.createCharmElement(expectedParentId);
    document.getRootElement().add(oldParentElement);
    ICharm[] oldCharms = builder.buildCharms(document, new ArrayList<ICharm>(), rules);
    assertEquals(2, oldCharms.length);
    assertTrue(connectionExists(oldCharms[0], oldCharms[1]));
    List<ICharm> existingCharms = new ArrayList<ICharm>();
    existingCharms.add(oldCharms[0]);
    existingCharms.add(oldCharms[1]);
    document.setRootElement(new DefaultElement("Root")); //$NON-NLS-1$
    Element newParentElement = CharmXmlTestUtils.createCharmElement(expectedParentId);
    document.getRootElement().add(newParentElement);
    assertEquals(1, document.getRootElement().elements().size());
    ICharm[] newCharms = builder.buildCharms(document, existingCharms, rules);
    assertEquals(2, newCharms.length);
    assertTrue(connectionExists(newCharms[0], newCharms[1]));
    assertTrue(connectionExists(oldCharms[0], oldCharms[1]));
    assertFalse(connectionExists(newCharms[0], oldCharms[0]));
    assertFalse(connectionExists(newCharms[1], oldCharms[1]));
    assertFalse(connectionExists(newCharms[0], oldCharms[1]));
    assertFalse(connectionExists(newCharms[1], oldCharms[0]));
  }

  private Charm getTestCharm(String id, CharmType type, String parentId) {
    final String[] parentArray = parentId == null ? new String[0] : new String[] { parentId };
    CharmPrerequisiteList list = new CharmPrerequisiteList(
        new IGenericTrait[] { new ValuedTraitType(AbilityType.Archery, 1) },
        new ValuedTraitType(OtherTraitType.Essence, 1),
        parentArray,
        new SelectiveCharmGroupTemplate[0],
        new ICharmAttributeRequirement[0]);
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(type);
    final Charm charm = new Charm(CharacterType.SOLAR, id, "TestGroup", //$NON-NLS-1$
        list,
        new CostList(null, null, null),
        new PermanentCostList(null, null, null, null),
        new ComboRestrictions(),
        SimpleDuration.INSTANT_DURATION,
        model,
        new IExaltedSourceBook[] { ExaltedSourceBook.ABEarth });
    return charm;
  }
}