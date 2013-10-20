package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmXMLConstants;
import net.sf.anathema.character.main.magic.charm.prerequisite.DirectCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.AttributePrerequisiteBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.PrerequisiteListBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PrerequisiteListBuilderTest {

  private static Element createBasicCharmPrerequisiteXml() {
    Element prerequisiteListElement = DocumentHelper.createElement("prerequisiteListElement");
    CharmXmlTestUtils.fillBasicPrerequisites(prerequisiteListElement);
    return prerequisiteListElement;
  }

  private static CharmPrerequisiteList parsePrerequisiteList(Element prerequisiteListElement) throws PersistenceException {
    return new PrerequisiteListBuilder(new TraitPrerequisitesBuilder(), new AttributePrerequisiteBuilder(), new CharmPrerequisiteBuilder())
            .buildPrerequisiteList(prerequisiteListElement);
  }

  @Test
  public void testCorrectCharmWithPrerequisiteCharm() throws Exception {
    Element prerequisiteListElement = createBasicCharmPrerequisiteXml();
    String correctId = "Solar.CorrectId";
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId);
    CharmPrerequisiteList prerequisiteList = parsePrerequisiteList(prerequisiteListElement);
    assertThat(prerequisiteList.getCharmPrerequisites().length, is(1));
    Charm actualParentCharm = ((DirectCharmLearnPrerequisite) prerequisiteList.getCharmPrerequisites()[0]).getDirectPredecessors()[0];
    assertThat(actualParentCharm.getId(), is(correctId));
  }

  @Test
  public void testCorrectCharmWithMultiplePrerequisiteCharms() throws Exception {
    Element prerequisiteListElement = createBasicCharmPrerequisiteXml();
    String correctId1 = "Solar.CorrectId1";
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId1);
    String correctId2 = "Solar.CorrectId2";
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId2);
    CharmPrerequisiteList prerequisiteList = parsePrerequisiteList(prerequisiteListElement);
    assertThat(prerequisiteList.getCharmPrerequisites().length, is(2));
    Charm[] parentCharms = ((DirectCharmLearnPrerequisite) prerequisiteList.getCharmPrerequisites()[0]).getDirectPredecessors();
    Charm firstActualParent = parentCharms[0];
    Charm secondActualParent = parentCharms[1];
    assertThat(firstActualParent.getId(), is(correctId1));
    assertThat(secondActualParent.getId(), is(correctId2));
  }
}