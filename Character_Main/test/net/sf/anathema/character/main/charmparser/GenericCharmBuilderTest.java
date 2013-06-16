package net.sf.anathema.character.main.charmparser;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.GenericCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.GenericCharmPrerequisiteBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.SpecialCharmBuilder;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterTypes;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GenericCharmBuilderTest {

  private final DummyCharacterTypes characterTypes = new DummyCharacterTypes();
  private final GenericCharmBuilder builder =
          new GenericCharmBuilder(new GenericIdStringBuilder(), new GenericTraitPrerequisitesBuilder(), new GenericAttributeRequirementBuilder(),
                  new GenericComboRulesBuilder(), new GenericCharmPrerequisiteBuilder(), characterTypes, mock(SpecialCharmBuilder.class));

  @Before
  public void setUp() throws Exception {
    characterTypes.add(new DummyExaltCharacterType());
  }

  @Test
  public void testReadGenericCharmId() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Archery);
    Charm charm = builder.buildCharm(xml);
    assertEquals("Dummy.Generic.Archery", charm.getId());
  }

  private void removeAttribute(Element element, String attribute) {
    element.remove(element.attribute(attribute));
  }

  @Test
  public void testReadGenericCharmGroup() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Archery);
    removeAttribute(xml, "group");
    Charm charm = builder.buildCharm(xml);
    assertEquals("Archery", charm.getGroupId());
  }

  @Test
  public void testReadGenericPrerequisiteFirst() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Archery);
    Element prerequisites = xml.element("prerequisite");
    removeAttribute(prerequisites.element("trait"), "id");
    Charm charm = builder.buildCharm(xml);
    assertEquals(AbilityType.Archery, charm.getPrimaryTraitType());
  }

  @Test
  public void testReadGenericPrerequisiteSecond() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Athletics);
    Element prerequisites = xml.element("prerequisite");
    removeAttribute(prerequisites.element("trait"), "id");
    Charm charm = builder.buildCharm(xml);
    assertEquals(AbilityType.Athletics, charm.getPrimaryTraitType());
  }

  @Test
  public void testReadGenericPrerequisiteHigherValue() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Archery);
    Element prerequisites = xml.element("prerequisite");
    prerequisites.element("trait").addAttribute("value", "3");
    Charm charm = builder.buildCharm(xml);
    assertEquals(3, charm.getPrerequisites()[0].getCurrentValue());
  }
}