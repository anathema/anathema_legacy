package net.sf.anathema.character.main.charmparser;

import net.sf.anathema.character.main.magic.CharmAttribute;
import net.sf.anathema.character.main.magic.CharmAttributeRequirement;
import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GenericAttributeRequirementBuilderTest {

  @Test
  public void testReadGenericAttributeRequirement() throws Exception {
    String xml = "<element><genericCharmAttributeRequirement attribute=\"generic\"/></element>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAttributeRequirementBuilder builder = new GenericAttributeRequirementBuilder();
    builder.setType(AbilityType.Investigation);
    IndirectCharmRequirement[] indirectRequirements = builder.getCharmAttributeRequirements(rootElement);
    assertTrue(ArrayUtils.contains(indirectRequirements, new CharmAttributeRequirement(new CharmAttribute("genericInvestigation", false), 1)));
  }
}