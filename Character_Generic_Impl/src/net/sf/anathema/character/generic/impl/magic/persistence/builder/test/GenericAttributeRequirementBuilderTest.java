package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GenericAttributeRequirementBuilderTest extends BasicTestCase {

  public void testReadGenericAttributeRequirement() throws Exception {
    String xml = "<element><genericAttributeRequirement attribute=\"generic\"/></element>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAttributeRequirementBuilder builder = new GenericAttributeRequirementBuilder();
    builder.setType(AbilityType.Investigation);
    ICharmAttributeRequirement[] charmAttributeRequirements = builder.getCharmAttributeRequirements(rootElement);
    assertTrue(ArrayUtilities.contains(charmAttributeRequirements, new CharmAttributeRequirement(new CharmAttribute(
        "genericInvestigation", //$NON-NLS-1$
        false), 1)));
  }
}