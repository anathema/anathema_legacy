package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.util.CostParser;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;

import org.dom4j.Element;

public class CostParserTest extends BasicTemplateParsingTestCase {

  private CostParser costParser;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.costParser = new CostParser();
  }

  public void testRequiredFixedCostOne() throws Exception {
    final Element rootElement = getDocumentRoot("<template><cost><fixedCost cost=\"1\"/></cost></template>"); //$NON-NLS-1$
    assertEquals(1, costParser.getFixedCostFromRequiredElement(rootElement, "cost")); //$NON-NLS-1$
  }

  public void testRequiredFixedCostTwo() throws Exception {
    final Element rootElement = getDocumentRoot("<template><cost><fixedCost cost=\"2\"/></cost></template>"); //$NON-NLS-1$
    assertEquals(2, costParser.getFixedCostFromRequiredElement(rootElement, "cost")); //$NON-NLS-1$
  }

  public void testPersistenceExepctionOnReadRequiredFixedCostFromMissingElement() throws Exception {
    final Element rootElement = getDocumentRoot("<template />"); //$NON-NLS-1$
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        costParser.getFixedCostFromRequiredElement(rootElement, "cost"); //$NON-NLS-1$
      }
    });
  }

  public void testPersistenceExepctionOnReadRequiredFixedCostWithMissingFixedCostElement() throws Exception {
    final Element rootElement = getDocumentRoot("<template><cost /></template>"); //$NON-NLS-1$
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        costParser.getFixedCostFromRequiredElement(rootElement, "cost"); //$NON-NLS-1$
      }
    });
  }
}