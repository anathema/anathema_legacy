package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.disy.commons.core.testing.ExceptionConvertingBlock;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GenericIdStringBuilderTest extends BasicTestCase {

  public void testGenerateHeaderString() throws Exception {
    GenericIdStringBuilder builder = new GenericIdStringBuilder();
    builder.setType(AbilityType.Brawl);
    String xml = "<charm id=\"test\"/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement);
    assertEquals("testBrawl", id); //$NON-NLS-1$
  }

  public void testTypeNotSet() throws Exception {
    assertThrowsException(IllegalStateException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        GenericIdStringBuilder builder = new GenericIdStringBuilder();
        String xml = "<charm id=\"test\"/>"; //$NON-NLS-1$
        Element rootElement = DocumentUtilities.read(xml).getRootElement();
        builder.build(rootElement);
      }
    });
  }
}