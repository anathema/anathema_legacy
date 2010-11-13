package net.sf.anathema.test.character.generic.persistence.magic.load;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.GenericCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.GenericCharmPrerequisiteBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.Element;

public class GenericCharmBuilderTest extends BasicTestCase {
  GenericCharmBuilder builder = new GenericCharmBuilder(
      new GenericIdStringBuilder(),
      new GenericTraitPrerequisitesBuilder(),
      new GenericAttributeRequirementBuilder(),
      new GenericComboRulesBuilder(),
      new GenericCharmPrerequisiteBuilder());

  public void testReadGenericCharmId() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Archery);
    Charm charm = builder.buildCharm(xml);
    assertEquals("Solar.Generic.Archery", charm.getId()); //$NON-NLS-1$
  }

  private void removeAttribute(Element element, String attribute) {
    element.remove(element.attribute(attribute));
  }

  public void testReadGenericCharmGroup() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Archery);
    removeAttribute(xml, "group"); //$NON-NLS-1$
    Charm charm = builder.buildCharm(xml);
    assertEquals("Archery", charm.getGroupId()); //$NON-NLS-1$
  }

  public void testReadGenericPrerequisiteFirst() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Archery);
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    removeAttribute(prerequisites.element("trait"), "id"); //$NON-NLS-1$ //$NON-NLS-2$
    Charm charm = builder.buildCharm(xml);
    assertEquals(AbilityType.Archery, charm.getPrimaryTraitType());
  }

  public void testReadGenericPrerequisiteSecond() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Athletics);
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    removeAttribute(prerequisites.element("trait"), "id"); //$NON-NLS-1$//$NON-NLS-2$
    Charm charm = builder.buildCharm(xml);
    assertEquals(AbilityType.Athletics, charm.getPrimaryTraitType());
  }

  public void testReadGenericPrerequisiteHigherValue() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Solar.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Archery);
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    prerequisites.element("trait").addAttribute("value", "3"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    Charm charm = builder.buildCharm(xml);
    assertEquals(3, charm.getPrerequisites()[0].getCurrentValue());
  }
}