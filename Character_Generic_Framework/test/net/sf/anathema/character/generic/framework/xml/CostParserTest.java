package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.util.CostParser;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CostParserTest extends BasicTemplateParsingTestCase {

  private CostParser costParser;

  @Before
  public void setUp() throws Exception {
    this.costParser = new CostParser();
  }

  @Test
  public void testRequiredFixedCostOne() throws Exception {
    final Element rootElement = getDocumentRoot("<template><cost><fixedCost cost=\"1\"/></cost></template>"); //$NON-NLS-1$
    assertEquals(1, costParser.getFixedCostFromRequiredElement(rootElement, "cost")); //$NON-NLS-1$
  }

  @Test
  public void testRequiredFixedCostTwo() throws Exception {
    final Element rootElement = getDocumentRoot("<template><cost><fixedCost cost=\"2\"/></cost></template>"); //$NON-NLS-1$
    assertEquals(2, costParser.getFixedCostFromRequiredElement(rootElement, "cost")); //$NON-NLS-1$
  }

  @Test(expected = PersistenceException.class)
  public void testPersistenceExepctionOnReadRequiredFixedCostFromMissingElement() throws Exception {
    final Element rootElement = getDocumentRoot("<template />"); //$NON-NLS-1$
    costParser.getFixedCostFromRequiredElement(rootElement, "cost"); //$NON-NLS-1$
  }

  @Test(expected = PersistenceException.class)
  public void testPersistenceExepctionOnReadRequiredFixedCostWithMissingFixedCostElement() throws Exception {
    final Element rootElement = getDocumentRoot("<template><cost /></template>"); //$NON-NLS-1$
    costParser.getFixedCostFromRequiredElement(rootElement, "cost"); //$NON-NLS-1$
  }
}