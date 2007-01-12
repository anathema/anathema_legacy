package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.creation.BonusPointCostTemplateParser;
import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.dummy.character.template.DummyXmlTemplateRegistry;
import net.sf.anathema.dummy.character.trait.DummyFavorableGenericTrait;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.junit.Test;

public class BonusPointCostTemplateParserTest extends BasicTemplateParsingTestCase {

  private static final String ORIGINAL_TEMPLATE_ID = "original"; //$NON-NLS-1$
  private BonusPointCostTemplateParser parser;
  private DummyXmlTemplateRegistry<GenericBonusPointCosts> registry;
  private GenericBonusPointCosts originalTemplate;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.registry = new DummyXmlTemplateRegistry<GenericBonusPointCosts>();
    this.parser = new BonusPointCostTemplateParser(registry, MartialArtsLevel.Celestial);
    originalTemplate = new GenericBonusPointCosts();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
  }

  public void testUsedTemplateRemainsUnmodifiedWithParsing() throws Exception {
    originalTemplate.setAttributeCost(3, 3);
    String changeContent = "<attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes>"; //$NON-NLS-1$
    GenericBonusPointCosts parsedTemplate = parser.parseTemplate(getDocumentRoot(createUsesOriginalTemplate(changeContent)));
    assertEquals(3, originalTemplate.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
  }

  public void testNoFavoredAttributeCost() throws Exception {
    String xml = "<root><attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes></root>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    GenericBonusPointCosts costs = parser.parseTemplate(element);
    assertEquals(4, costs.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, costs.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, true)));
  }

  public void testMissingFavoredValueIsOverwrittenByNewGeneralValue() throws Exception {
    originalTemplate.setAttributeCost(3, 3);
    String changeContent = "<attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute></attributes>"; //$NON-NLS-1$
    GenericBonusPointCosts parsedTemplate = parser.parseTemplate(getDocumentRoot(createUsesOriginalTemplate(changeContent)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(4, parsedTemplate.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, true)));
  }

  public void testFavoredAttributeCostSpecified() throws Exception {
    String xml = "<root><attributes><generalAttribute><fixedCost cost=\"4\" /></generalAttribute><favoredAttribute><fixedCost cost=\"3\" /></favoredAttribute></attributes></root>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    GenericBonusPointCosts costs = parser.parseTemplate(element);
    assertEquals(4, costs.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, false)));
    assertEquals(3, costs.getAttributeCosts(new DummyFavorableGenericTrait(AttributeType.Wits, 1, true)));
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

  @Test
  public void testBasicCharmCostIsSetAndMartialArtsCostEquals() throws Exception {
    String xml = "<root><charms><generalCharms><fixedCost cost=\"7\" /></generalCharms><favoredCharms><fixedCost cost=\"5\" /></favoredCharms></charms></root>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    GenericBonusPointCosts costs = parser.parseTemplate(element);
    DummyCharm testCharm = new DummyCharm("test"); //$NON-NLS-1$
    assertEquals(7, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return null;
      }

      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(5, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return null;
      }

      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(7, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Celestial;
      }

      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(5, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Celestial;
      }

      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(7, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Sidereal;
      }

      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(5, costs.getMagicCosts(testCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Sidereal;
      }

      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
  }

  @Test
  public void testBasicCharmCostIsSetAndMartialArtsCostOverrides() throws Exception {
    String xml = "<root><charms><generalCharms><fixedCost cost=\"7\" /></generalCharms><favoredCharms><fixedCost cost=\"5\" /></favoredCharms><generalHighLevelMartialArtsCharms><fixedCost cost=\"10\"/></generalHighLevelMartialArtsCharms>" //$NON-NLS-1$
        + "<favoredHighLevelMartialArtsCharms><fixedCost cost=\"7\"/></favoredHighLevelMartialArtsCharms></charms></root>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    GenericBonusPointCosts costs = parser.parseTemplate(element);
    DummyCharm dummyCharm = new DummyCharm("test"); //$NON-NLS-1$
    assertEquals(7, costs.getMagicCosts(dummyCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return null;
      }

      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(5, costs.getMagicCosts(dummyCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return null;
      }

      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(7, costs.getMagicCosts(dummyCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Celestial;
      }

      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(5, costs.getMagicCosts(dummyCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Celestial;
      }

      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(10, costs.getMagicCosts(dummyCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Sidereal;
      }

      public boolean isMagicFavored(IMagic magic) {
        return false;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
    assertEquals(7, costs.getMagicCosts(dummyCharm, new ICostAnalyzer() {
      public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
        return MartialArtsLevel.Sidereal;
      }

      public boolean isMagicFavored(IMagic magic) {
        return true;
      }

      public boolean isOccultFavored() {
        return false;
      }
    }));
  }
}