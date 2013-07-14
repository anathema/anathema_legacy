package net.sf.anathema.hero.template.parser;

import net.sf.anathema.hero.dummy.DummyGenericTrait;
import net.sf.anathema.hero.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.xml.creation.BonusPointCostTemplateParser;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class BonusPointCostTemplateParserTest extends BasicTemplateParsingTestCase {

  private static final String ORIGINAL_TEMPLATE_ID = "original";
  private BonusPointCostTemplateParser parser;
  private GenericBonusPointCosts originalTemplate;
  private CostAnalyzer costAnalyzerMock = mock(CostAnalyzer.class);

  @Before
  public void setUp() throws Exception {
    DummyXmlTemplateRegistry<GenericBonusPointCosts> registry = new DummyXmlTemplateRegistry<>();
    this.parser = new BonusPointCostTemplateParser(registry, MartialArtsLevel.Celestial);
    originalTemplate = new GenericBonusPointCosts();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
  }

  @Test
  public void testUsedTemplateRemainsUnmodifiedWithParsing() throws Exception {
    originalTemplate.setAttributeCost(3, 3);
    String changeContent = "<attributes>" +
                           "<generalAttribute><fixedCost cost=\"4\" /></generalAttribute>" +
                           "</attributes>";
    GenericBonusPointCosts parsedTemplate = parser.parseTemplate(getDocumentRoot(createUsesOriginalTemplate(changeContent)));
    assertEquals(3, originalTemplate.getAttributeCosts(new DummyGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyGenericTrait(AttributeType.Wits, 1, false)));
  }

  @Test
  public void testNoFavoredAttributeCost() throws Exception {
    String xml = "<root><attributes>" +
                 "<generalAttribute><fixedCost cost=\"4\" /></generalAttribute>" +
                 "</attributes></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(4, costs.getAttributeCosts(new DummyGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, costs.getAttributeCosts(new DummyGenericTrait(AttributeType.Wits, 1, true)));
  }

  @Test
  public void testMissingFavoredValueIsOverwrittenByNewGeneralValue() throws Exception {
    originalTemplate.setAttributeCost(3, 3);
    String changeContent = "<attributes>" +
                           "<generalAttribute><fixedCost cost=\"4\" /></generalAttribute>" +
                           "</attributes>";
    GenericBonusPointCosts parsedTemplate = parser.parseTemplate(getDocumentRoot(createUsesOriginalTemplate(changeContent)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyGenericTrait(AttributeType.Wits, 1, true)));
  }

  @Test
  public void testFavoredAttributeCostSpecified() throws Exception {
    String xml = "<root><attributes>" +
                 "<generalAttribute><fixedCost cost=\"4\" /></generalAttribute>" +
                 "<favoredAttribute><fixedCost cost=\"3\" /></favoredAttribute>" +
                 "</attributes></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(4, costs.getAttributeCosts(new DummyGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(3, costs.getAttributeCosts(new DummyGenericTrait(AttributeType.Wits, 1, true)));
  }

  private String createUsesOriginalTemplate(String content) {
    return "<root uses=\"original\">" + content + "</root>";
  }

  @Test
  public void testMaximumFreeVirtueDotsUnchanged() throws Exception {
    String xml = "<root><advantages><virtues><fixedCost cost=\"3\"/></virtues></advantages></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(3, costs.getVirtueCosts().getRatingCosts(1));
    assertEquals(3, costs.getMaximumFreeVirtueRank());
  }

  @Test
  public void testMaximumFreeVirtueDotsChanged() throws Exception {
    String xml = "<root><advantages>" +
                 "<virtues><fixedCost cost=\"3\"/>" +
                 "<maximumFreeVirtueRank rank=\"4\"/></virtues>" +
                 "</advantages></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(3, costs.getVirtueCosts().getRatingCosts(1));
    assertEquals(4, costs.getMaximumFreeVirtueRank());
  }

  @Test
  public void parsesGeneralCharmCosts() throws Exception {
    String xml = "<root><charms>" +
                 "<generalCharms><fixedCost cost=\"7\" /></generalCharms>" +
                 "<favoredCharms><fixedCost cost=\"5\" /></favoredCharms>" +
                 "<generalHighLevelMartialArtsCharms><fixedCost cost=\"10\"/></generalHighLevelMartialArtsCharms>" +
                 "<favoredHighLevelMartialArtsCharms><fixedCost cost=\"8\"/></favoredHighLevelMartialArtsCharms>" +
                 "</charms></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertThat(7, is(costs.charmCosts.general.charmCost));
  }

  @Test
  public void parsesFavoredCharmCosts() throws Exception {
    String xml = "<root><charms>" +
                 "<generalCharms><fixedCost cost=\"7\" /></generalCharms>" +
                 "<favoredCharms><fixedCost cost=\"5\" /></favoredCharms>" +
                 "<generalHighLevelMartialArtsCharms><fixedCost cost=\"10\"/></generalHighLevelMartialArtsCharms>" +
                 "<favoredHighLevelMartialArtsCharms><fixedCost cost=\"8\"/></favoredHighLevelMartialArtsCharms>" +
                 "</charms></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertThat(5, is(costs.charmCosts.favored.charmCost));
  }

  @Test
  public void parsesGeneralHighMartialArtsCosts() throws Exception {
    String xml = "<root><charms>" +
                 "<generalCharms><fixedCost cost=\"7\" /></generalCharms>" +
                 "<favoredCharms><fixedCost cost=\"5\" /></favoredCharms>" +
                 "<generalHighLevelMartialArtsCharms><fixedCost cost=\"10\"/></generalHighLevelMartialArtsCharms>" +
                 "<favoredHighLevelMartialArtsCharms><fixedCost cost=\"8\"/></favoredHighLevelMartialArtsCharms>" +
                 "</charms></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(10, costs.getMagicCosts().general.highLevelMartialArtsCost);
  }

  @Test
  public void parsesFavoredHighMartialArtsCosts() throws Exception {
    String xml = "<root><charms>" +
                 "<generalCharms><fixedCost cost=\"7\" /></generalCharms>" +
                 "<favoredCharms><fixedCost cost=\"5\" /></favoredCharms>" +
                 "<generalHighLevelMartialArtsCharms><fixedCost cost=\"10\"/></generalHighLevelMartialArtsCharms>" +
                 "<favoredHighLevelMartialArtsCharms><fixedCost cost=\"8\"/></favoredHighLevelMartialArtsCharms>" +
                 "</charms></root>";
    GenericBonusPointCosts costs = parseXmlToCost(xml);
    assertEquals(8, costs.getMagicCosts().favored.highLevelMartialArtsCost);
  }

  private GenericBonusPointCosts parseXmlToCost(String xml) throws AnathemaException {
    Element element = DocumentUtilities.read(xml).getRootElement();
    return parser.parseTemplate(element);
  }
}