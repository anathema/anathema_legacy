package net.sf.anathema.test.character.generic.persistence.magic.load;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.junit.Test;

public class GenericIdStringBuilderTest {

	@Test
  public void testGenerateHeaderString() throws Exception {
    GenericIdStringBuilder builder = new GenericIdStringBuilder();
    builder.setType(AbilityType.Brawl);
    String xml = "<charm id=\"test\"/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement);
    assertEquals("test.Brawl", id); //$NON-NLS-1$
  }
	@Test(expected=IllegalStateException.class)
  public void testTypeNotSet() throws Exception {
        GenericIdStringBuilder builder = new GenericIdStringBuilder();
        String xml = "<charm id=\"test\"/>"; //$NON-NLS-1$
        Element rootElement = DocumentUtilities.read(xml).getRootElement();
        builder.build(rootElement);
  }
}