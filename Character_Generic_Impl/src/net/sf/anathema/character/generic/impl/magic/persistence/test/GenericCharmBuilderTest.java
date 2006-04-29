package net.sf.anathema.character.generic.impl.magic.persistence.test;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.Element;

public class GenericCharmBuilderTest extends BasicTestCase {

  private GenericCharmBuilder builder = new GenericCharmBuilder();

  public void testReadGenericCharmId() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    Charm charm = builder.buildCharm(xml, AbilityType.Archery);
    assertEquals("Solar.GenericArchery", charm.getId()); //$NON-NLS-1$
  }

  private void removeAttribute(Element element, String attribute) {
    element.remove(element.attribute(attribute));
  }

  private void removeElement(Element element, String name) {
    element.remove(element.element(name));
  }

  public void testReadGenericCharmGroup() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    removeElement(xml, "group"); //$NON-NLS-1$
    Charm charm = builder.buildCharm(xml, AbilityType.Archery);
    assertEquals("Archery", charm.getGroupId()); //$NON-NLS-1$
  }

  public void testReadGenericPrerequisiteFirst() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    removeAttribute(prerequisites.element("trait"), "id"); //$NON-NLS-1$ //$NON-NLS-2$
    Charm charm = builder.buildCharm(xml, AbilityType.Archery);
    assertEquals(AbilityType.Archery, charm.getPrerequisites()[0].getType());
  }

  public void testReadGenericPrerequisiteSecond() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    removeAttribute(prerequisites.element("trait"), "id"); //$NON-NLS-1$//$NON-NLS-2$
    Charm charm = builder.buildCharm(xml, AbilityType.Athletics);
    assertEquals(AbilityType.Athletics, charm.getPrerequisites()[0].getType());
  }

  // Fails
  public void testReadGenericPrerequisiteHigherValue() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    prerequisites.element("trait").addAttribute("value", "3"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    Charm charm = builder.buildCharm(xml, AbilityType.Awareness);
    assertEquals(3, charm.getPrerequisites()[0].getCurrentValue());
  }
}