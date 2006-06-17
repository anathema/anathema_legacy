package net.sf.anathema.test.character.generic.persistence.magic.save;

import java.util.List;

import net.sf.anathema.character.generic.impl.magic.persistence.writer.PrerequisiteWriter;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.dummy.character.charm.DummyCharm;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class PrerequisiteWriterTest extends BasicTestCase {
  private PrerequisiteWriter writer;
  private Element element;
  private DummyCharm charm;

  @Override
  protected void setUp() throws Exception {
    writer = new PrerequisiteWriter();
    element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
    charm = new DummyCharm();
  }

  public void testNoPrerequisites() throws Exception {
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        writer.write(charm, element);
      }
    });
  }

  public void testEssencePrerequisite() throws Exception {
    charm.setEssence(new ValuedTraitType(OtherTraitType.Essence, 4));
    writer.write(charm, element);
    assertEquals(4, ElementUtilities.getRequiredIntAttrib(element.element("prerequisite").element("essence"), "value")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  public void testTraitPrerequisites() throws Exception {
    charm.setEssence(new ValuedTraitType(OtherTraitType.Essence, 4));
    charm.setPrerequisites(new ValuedTraitType[] {
        new ValuedTraitType(AbilityType.Archery, 2),
        new ValuedTraitType(AttributeType.Strength, 3) });
    writer.write(charm, element);
    List<Element> traitPrerequisites = ElementUtilities.elements(element.element("prerequisite"), "trait"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals(2, traitPrerequisites.size());
    assertEquals(AbilityType.Archery.getId(), traitPrerequisites.get(0).attributeValue("id")); //$NON-NLS-1$
    assertEquals(2, ElementUtilities.getRequiredIntAttrib(traitPrerequisites.get(0), "value")); //$NON-NLS-1$
    assertEquals(AttributeType.Strength.getId(), traitPrerequisites.get(1).attributeValue("id")); //$NON-NLS-1$
    assertEquals(3, ElementUtilities.getRequiredIntAttrib(traitPrerequisites.get(1), "value")); //$NON-NLS-1$
  }

  public void testCharmPrerequisites() throws Exception {
    String name1 = "aCharm"; //$NON-NLS-1$
    String name2 = "bCharm"; //$NON-NLS-1$
    charm = new DummyCharm("charm", new DummyCharm[] { new DummyCharm(name1), new DummyCharm(name2) }); //$NON-NLS-1$
    charm.setEssence(new ValuedTraitType(OtherTraitType.Essence, 4));
    writer.write(charm, element);
    List<Element> charmPrerequisites = ElementUtilities.elements(element.element("prerequisite"), "charmReference"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals(2, charmPrerequisites.size());
    assertEquals(name1, charmPrerequisites.get(0).attributeValue("id")); //$NON-NLS-1$
    assertEquals(name2, charmPrerequisites.get(1).attributeValue("id")); //$NON-NLS-1$
  }
}
