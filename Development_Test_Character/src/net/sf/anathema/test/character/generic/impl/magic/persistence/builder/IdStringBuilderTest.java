package net.sf.anathema.test.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.IIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.IdStringBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class IdStringBuilderTest extends BasicTestCase {

  private IIdStringBuilder builder = new IdStringBuilder();

  public void testIdPresent() throws Exception {
    String xml = "<charm id=\"test\"/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement);
    assertEquals("test", id); //$NON-NLS-1$
  }

  public void testIdVariable() throws Exception {
    String xml = "<charm id=\"otherTest\"/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement);
    assertEquals("otherTest", id); //$NON-NLS-1$
  }

  public void testIdMissing() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        String xml = "<charm />"; //$NON-NLS-1$
        Element rootElement = DocumentUtilities.read(xml).getRootElement();
        builder.build(rootElement);
      }
    });
  }

  public void testBadId() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        String xml = "<charm id=\"\"/>"; //$NON-NLS-1$
        Element rootElement = DocumentUtilities.read(xml).getRootElement();
        builder.build(rootElement);
      }
    });
  }
}