package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisiteBuilder;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GenericTraitPrerequisiteBuilderTest extends BasicTestCase {

  public void testTraitPrerequisiteBuilder() throws Exception {
    String xml = "<trait  value=\"3\"/>";//$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitPrerequisiteBuilder builder = new GenericTraitPrerequisiteBuilder();
    builder.setType(AbilityType.Larceny);
    IGenericTrait trait = builder.build(rootElement);
    assertEquals(AbilityType.Larceny, trait.getType());
    assertEquals(3, trait.getCurrentValue());
  }
}