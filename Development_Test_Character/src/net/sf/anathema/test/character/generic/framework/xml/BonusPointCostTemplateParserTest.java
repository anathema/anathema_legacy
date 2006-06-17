package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.creation.BonusPointCostTemplateParser;
import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.test.character.dummy.DummyXmlTemplateRegistry;

import org.dom4j.Element;

public class BonusPointCostTemplateParserTest extends BasicTemplateParsingTestCase {

  private static final String ORIGINAL_TEMPLATE_ID = "original"; //$NON-NLS-1$
  private BonusPointCostTemplateParser parser;
  private DummyXmlTemplateRegistry<GenericBonusPointCosts> registry;
  private GenericBonusPointCosts originalTemplate;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.registry = new DummyXmlTemplateRegistry<GenericBonusPointCosts>();
    this.parser = new BonusPointCostTemplateParser(registry);
    originalTemplate = new GenericBonusPointCosts();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
  }

  public void testUsedTemplateRemainsUnmodifiedWithParsing() throws Exception {
    originalTemplate.setAttributeCost(3, 3);
    String changeContent = "<attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes>"; //$NON-NLS-1$
    GenericBonusPointCosts parsedTemplate = parser.parseTemplate(getDocumentRoot(createUsesOriginalTemplate(changeContent)));
    assertEquals(3, originalTemplate.getAttributeCosts(false).getRatingCosts(1));
    assertEquals(4, parsedTemplate.getAttributeCosts(false).getRatingCosts(1));
  }

  public void testNoFavoredAttributeCost() throws Exception {
    String xml = "<root><attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes></root>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    GenericBonusPointCosts costs = parser.parseTemplate(element);
    assertEquals(4, costs.getAttributeCosts(false).getRatingCosts(1));
    assertEquals(4, costs.getAttributeCosts(true).getRatingCosts(1));
  }

  public void testMissingFavoredValueIsOverwrittenByNewGeneralValue() throws Exception {
    originalTemplate.setAttributeCost(3, 3);
    String changeContent = "<attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes>"; //$NON-NLS-1$
    GenericBonusPointCosts parsedTemplate = parser.parseTemplate(getDocumentRoot(createUsesOriginalTemplate(changeContent)));
    assertEquals(4, parsedTemplate.getAttributeCosts(false).getRatingCosts(1));
    assertEquals(4, parsedTemplate.getAttributeCosts(true).getRatingCosts(1));
  }

  public void testFavoredAttributeCostSpecified() throws Exception {
    String xml = "<root><attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute><favoredAttribute><fixedCost cost=\"3\" /></favoredAttribute></attributes></root>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    GenericBonusPointCosts costs = parser.parseTemplate(element);
    assertEquals(4, costs.getAttributeCosts(false).getRatingCosts(1));
    assertEquals(3, costs.getAttributeCosts(true).getRatingCosts(1));
  }

  private String createUsesOriginalTemplate(String content) {
    return "<root uses=\"original\">" + content + "</root>"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void testMaximumFreeVirtueDotsUnchanged() throws Exception {
    String xml = "<root><advantages><virtues><fixedCost cost=\"3\"/></virtues></advantages></root>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    GenericBonusPointCosts costs = parser.parseTemplate(element);
    assertEquals(3, costs.getVirtueCosts().getRatingCosts(1));
    assertEquals(3, costs.getMaximumFreeVirtueRank());
  }

  public void testMaximumFreeVirtueDotsChanged() throws Exception {
    String xml = "<root><advantages><virtues><fixedCost cost=\"3\"/><maximumFreeRank rank=\"4\"/></virtues></advantages></root>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    GenericBonusPointCosts costs = parser.parseTemplate(element);
    assertEquals(3, costs.getVirtueCosts().getRatingCosts(1));
    assertEquals(4, costs.getMaximumFreeVirtueRank());
  }
}