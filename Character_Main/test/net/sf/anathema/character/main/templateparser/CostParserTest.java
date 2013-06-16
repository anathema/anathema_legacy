package net.sf.anathema.character.main.templateparser;

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
    final Element rootElement = getDocumentRoot("<template><cost><fixedCost cost=\"1\"/></cost></template>");
    assertEquals(1, costParser.getFixedCostFromRequiredElement(rootElement, "cost"));
  }

  @Test
  public void testRequiredFixedCostTwo() throws Exception {
    final Element rootElement = getDocumentRoot("<template><cost><fixedCost cost=\"2\"/></cost></template>");
    assertEquals(2, costParser.getFixedCostFromRequiredElement(rootElement, "cost"));
  }

  @Test(expected = PersistenceException.class)
  public void testPersistenceExepctionOnReadRequiredFixedCostFromMissingElement() throws Exception {
    final Element rootElement = getDocumentRoot("<template />");
    costParser.getFixedCostFromRequiredElement(rootElement, "cost");
  }

  @Test(expected = PersistenceException.class)
  public void testPersistenceExepctionOnReadRequiredFixedCostWithMissingFixedCostElement() throws Exception {
    final Element rootElement = getDocumentRoot("<template><cost /></template>");
    costParser.getFixedCostFromRequiredElement(rootElement, "cost");
  }
}