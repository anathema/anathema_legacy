package net.sf.anathema.character.generic.persistence.load.load;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GenericAttributeRequirementBuilderTest {

  @Test
  public void testReadGenericAttributeRequirement() throws Exception {
    String xml = "<element><genericCharmAttributeRequirement attribute=\"generic\"/></element>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAttributeRequirementBuilder builder = new GenericAttributeRequirementBuilder();
    builder.setType(AbilityType.Investigation);
    ICharmAttributeRequirement[] charmAttributeRequirements = builder.getCharmAttributeRequirements(rootElement);
    assertTrue(ArrayUtilities.containsValue(charmAttributeRequirements, new CharmAttributeRequirement(new CharmAttribute(
      "genericInvestigation", //$NON-NLS-1$
      false), 1)));
  }
}