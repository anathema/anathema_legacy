package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericHeaderStringBuilder;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GenericHeaderStringBuilderTest extends BasicTestCase {

  public void testGenerateHeaderString() throws Exception {
    GenericHeaderStringBuilder builder = new GenericHeaderStringBuilder("id", AbilityType.Brawl);
    String xml = "<charm id=\"test\"/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement);
    assertEquals("testBrawl", id); //$NON-NLS-1$
  }
}