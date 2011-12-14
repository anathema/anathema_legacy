package net.sf.anathema.character.generic.persistence.load.load;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.ITraitPrerequisiteBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.TraitPrerequisiteBuilder;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.junit.Test;

public class TraitPrerequisiteBuilderTest {

  private ITraitPrerequisiteBuilder builder = new TraitPrerequisiteBuilder();

  @Test
  public void testTraitPrerequisiteBuilder() throws Exception {
    String xml = "<trait id=\"Larceny\" value=\"3\"/>";//$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    IGenericTrait trait = builder.build(rootElement);
    assertEquals(AbilityType.Larceny, trait.getType());
    assertEquals(3, trait.getCurrentValue());
  }

  @Test(expected=PersistenceException.class)
  public void testValueMissing() throws Exception {
        String xml = "<trait id=\"Larceny\" />";//$NON-NLS-1$
        Element rootElement = DocumentUtilities.read(xml).getRootElement();
        builder.build(rootElement);
  }
}