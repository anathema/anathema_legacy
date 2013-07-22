package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.parser.charms.GenericCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.GenericCharmPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.parser.charms.GenericIdStringBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.ReflectionSpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.combos.GenericComboRulesBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.dummy.DummyCharacterTypes;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenericCharmBuilderTest {

  private final DummyCharacterTypes characterTypes = new DummyCharacterTypes();
  private ReflectionSpecialCharmParser specialCharmParserMock = mock(ReflectionSpecialCharmParser.class);
  private final GenericCharmBuilder builder =
          new GenericCharmBuilder(new GenericIdStringBuilder(), new GenericTraitPrerequisitesBuilder(), new GenericAttributeRequirementBuilder(),
                  new GenericComboRulesBuilder(), new GenericCharmPrerequisiteBuilder(), characterTypes, specialCharmParserMock);

  @Before
  public void setUp() throws Exception {
    characterTypes.add(new DummyExaltCharacterType());
    when(specialCharmParserMock.readCharmDto((Element) any(), (String) any())).thenReturn(new SpecialCharmDto());
  }

  @Test
  public void testReadGenericCharmId() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Archery);
    CharmImpl charm = builder.buildCharm(xml, new ArrayList<SpecialCharmDto>());
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
    CharmImpl charm = builder.buildCharm(xml, new ArrayList<SpecialCharmDto>());
    assertEquals("Archery", charm.getGroupId());
  }

  @Test
  public void testReadGenericPrerequisiteFirst() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Archery);
    Element prerequisites = xml.element("prerequisite");
    removeAttribute(prerequisites.element("trait"), "id");
    CharmImpl charm = builder.buildCharm(xml, new ArrayList<SpecialCharmDto>());
    assertEquals(AbilityType.Archery, charm.getPrimaryTraitType());
  }

  @Test
  public void testReadGenericPrerequisiteSecond() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Athletics);
    Element prerequisites = xml.element("prerequisite");
    removeAttribute(prerequisites.element("trait"), "id");
    CharmImpl charm = builder.buildCharm(xml, new ArrayList<SpecialCharmDto>());
    assertEquals(AbilityType.Athletics, charm.getPrimaryTraitType());
  }

  @Test
  public void testReadGenericPrerequisiteHigherValue() throws Exception {
    Element xml = CharmXmlTestUtils.createCharmElement("Dummy.Generic");
    builder.setType(AbilityType.Archery);
    Element prerequisites = xml.element("prerequisite");
    prerequisites.element("trait").addAttribute("value", "3");
    CharmImpl charm = builder.buildCharm(xml, new ArrayList<SpecialCharmDto>());
    assertEquals(3, charm.getPrerequisites()[0].getCurrentValue());
  }
}