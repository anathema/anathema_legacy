package net.sf.anathema.hero.charms.parser;

import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.GenericTraitPrerequisiteBuilder;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenericTraitPrerequisiteBuilderTest {

  @Test
  public void testTraitPrerequisiteBuilder() throws Exception {
    String xml = "<trait  value=\"3\"/>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitPrerequisiteBuilder builder = new GenericTraitPrerequisiteBuilder();
    builder.setType(AbilityType.Larceny);
    ValuedTraitType trait = builder.build(rootElement);
    assertEquals(AbilityType.Larceny, trait.getType());
    assertEquals(3, trait.getCurrentValue());
  }
}