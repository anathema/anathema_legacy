package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.parser.charms.GenericIdStringBuilder;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenericIdStringBuilderTest {

  @Test
  public void testGenerateHeaderString() throws Exception {
    GenericIdStringBuilder builder = new GenericIdStringBuilder();
    builder.setType(AbilityType.MartialArts);
    String xml = "<charm id=\"test\"/>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement);
    assertEquals("test.MartialArts", id);
  }

  @Test(expected = IllegalStateException.class)
  public void testTypeNotSet() throws Exception {
    GenericIdStringBuilder builder = new GenericIdStringBuilder();
    String xml = "<charm id=\"test\"/>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    builder.build(rootElement);
  }
}