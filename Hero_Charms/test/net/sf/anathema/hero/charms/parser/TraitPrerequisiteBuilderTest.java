package net.sf.anathema.hero.charms.parser;

import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.ITraitPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.TraitPrerequisiteBuilder;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TraitPrerequisiteBuilderTest {

  private ITraitPrerequisiteBuilder builder = new TraitPrerequisiteBuilder();

  @Test
  public void testTraitPrerequisiteBuilder() throws Exception {
    String xml = "<trait id=\"Larceny\" value=\"3\"/>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    ValuedTraitType trait = builder.build(rootElement);
    assertEquals(AbilityType.Larceny, trait.getType());
    assertEquals(3, trait.getCurrentValue());
  }

  @Test(expected = PersistenceException.class)
  public void testValueMissing() throws Exception {
    String xml = "<trait id=\"Larceny\" />";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    builder.build(rootElement);
  }
}