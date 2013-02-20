package net.sf.anathema.character.generic.persistence.load.load;

import net.sf.anathema.character.generic.dummy.DummyCharacterTypes;
import net.sf.anathema.character.generic.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.GenericCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.GenericCharmPrerequisiteBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.SpecialCharmBuilder;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GenericCharmBuilderTest {

  private final DummyCharacterTypes characterTypes = new DummyCharacterTypes();
  private final GenericCharmBuilder builder = new GenericCharmBuilder(
    new GenericIdStringBuilder(),
    new GenericTraitPrerequisitesBuilder(),
    new GenericAttributeRequirementBuilder(),
    new GenericComboRulesBuilder(),
    new GenericCharmPrerequisiteBuilder(), characterTypes, mock(SpecialCharmBuilder.class));

  @Before
  public void setUp() throws Exception {
    characterTypes.add(new DummyExaltCharacterType());
  }

  @Test
  public void testReadGenericCharmId() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Archery);
    Charm charm = builder.buildCharm(xml);
    assertEquals("Dummy.Generic.Archery", charm.getId()); //$NON-NLS-1$
  }

  private void removeAttribute(Element element, String attribute) {
    element.remove(element.attribute(attribute));
  }

  @Test
  public void testReadGenericCharmGroup() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Archery);
    removeAttribute(xml, "group"); //$NON-NLS-1$
    Charm charm = builder.buildCharm(xml);
    assertEquals("Archery", charm.getGroupId()); //$NON-NLS-1$
  }

  @Test
  public void testReadGenericPrerequisiteFirst() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Archery);
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    removeAttribute(prerequisites.element("trait"), "id"); //$NON-NLS-1$ //$NON-NLS-2$
    Charm charm = builder.buildCharm(xml);
    assertEquals(AbilityType.Archery, charm.getPrimaryTraitType());
  }

  @Test
  public void testReadGenericPrerequisiteSecond() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Athletics);
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    removeAttribute(prerequisites.element("trait"), "id"); //$NON-NLS-1$//$NON-NLS-2$
    Charm charm = builder.buildCharm(xml);
    assertEquals(AbilityType.Athletics, charm.getPrimaryTraitType());
  }

  @Test
  public void testReadGenericPrerequisiteHigherValue() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic"); //$NON-NLS-1$
    builder.setType(AbilityType.Archery);
    Element prerequisites = xml.element("prerequisite"); //$NON-NLS-1$
    prerequisites.element("trait").addAttribute("value", "3"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    Charm charm = builder.buildCharm(xml);
    assertEquals(3, charm.getPrerequisites()[0].getCurrentValue());
  }
}