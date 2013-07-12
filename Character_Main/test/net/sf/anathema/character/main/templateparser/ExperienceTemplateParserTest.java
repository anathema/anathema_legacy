package net.sf.anathema.character.main.templateparser;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.character.main.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.main.testing.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.experience.ExperienceTemplateParser;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExperienceTemplateParserTest {

  String xml = "<dummyExperienceTemplate>" + "<attributes>" + "<generalCosts>" + "<currentRating multiplier=\"4\" />" + "</generalCosts>" +
               "<favoredCosts>" + "<currentRating multiplier=\"3\" />" + "</favoredCosts>" + "</attributes>" + "<abilities>" + "  <generalCosts>" +
               "    <currentRating multiplier=\"2\" initialCosts=\"3\"/>" + "  </generalCosts>" + "  <favoredCosts>" +
               "    <currentRating multiplier=\"2\" summand=\"-1\" initialCosts=\"3\"/>" + "  </favoredCosts>" + "  <specialties>" +
               "    <fixedCost cost=\"3\" />" + "  </specialties>" + "</abilities>" + "<advantages>" + "<willpower>" +
               "<currentRating multiplier=\"2\" />" + "</willpower>" + "<virtues>" + "<currentRating multiplier=\"3\" />" + "</virtues>" +
               "<essence>" + "<currentRating multiplier=\"8\" />" + "</essence>" + "</advantages>" + "<magic>" +
               " <charms favored=\"8\" general=\"10\">" + "<highLevelMartialArts favored=\"13\" general=\"15\"/>" + "</charms>" + "</magic>" +
               "</dummyExperienceTemplate>";
  private DummyXmlTemplateRegistry<GenericExperiencePointCosts> templateRegistry;
  private ExperienceTemplateParser parser;

  @Before
  public void setUp() throws Exception {
    templateRegistry = new DummyXmlTemplateRegistry<>();
    parser = new ExperienceTemplateParser(templateRegistry, MartialArtsLevel.Celestial);
  }

  private Element createElement() throws AnathemaException {
    return DocumentUtilities.read(xml).getRootElement();
  }

  @Test
  public void testAttributeCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(new MultiplyRatingCosts(4), costs.getAttributeCosts(false));
  }

  @Test
  public void testGeneralAbilityCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(new MultiplyRatingCosts(2, 3), costs.getAbilityCosts(false));
  }

  @Test
  public void testFavoredAbilityCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(new MultiplyRatingCosts(2, 3, -1), costs.getAbilityCosts(true));
  }

  @Test
  public void testSpecialtyCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(3, costs.getSpecialtyCosts(false));
  }

  @Test
  public void testWillpowerCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(new MultiplyRatingCosts(2), costs.getWillpowerCosts());
  }

  @Test
  public void testVirtueCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(new MultiplyRatingCosts(3), costs.getVirtueCosts());
  }

  @Test
  public void testEssenceCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(new MultiplyRatingCosts(8), costs.getEssenceCosts());
  }

  @Test
  public void testGeneralMagicCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(10, costs.getCharmCosts(new DummyCharm("Charm"), new CostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(Magic magic) {
        return null;
      }

      @Override
      public boolean isMagicFavored(Magic magic) {
        return false;
      }
    }));
  }

  @Test
  public void testFavoredMagicCosts() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(8, costs.getCharmCosts(new DummyCharm("Charm"), new CostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(Magic magic) {
        return null;
      }

      @Override
      public boolean isMagicFavored(Magic magic) {
        return true;
      }
    }));
  }

  @Test
  public void testCelestialMartialArtsCost() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(10, costs.getCharmCosts(new DummyCharm("Charm"), new CostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(Magic magic) {
        return MartialArtsLevel.Celestial;
      }

      @Override
      public boolean isMagicFavored(Magic magic) {
        return false;
      }
    }));
  }

  @Test
  public void testUnfavoredHighLevelMartialArtsCost() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(15, costs.getCharmCosts(new DummyCharm("Charm"), new CostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(Magic magic) {
        return MartialArtsLevel.Sidereal;
      }

      @Override
      public boolean isMagicFavored(Magic magic) {
        return false;
      }
    }));
  }

  @Test
  public void testFavoredHighLevelMartialArtsCost() throws Exception {
    Element element = createElement();
    GenericExperiencePointCosts costs = parser.parseTemplate(element);
    assertEquals(13, costs.getCharmCosts(new DummyCharm("Charm"), new CostAnalyzer() {
      @Override
      public MartialArtsLevel getMartialArtsLevel(Magic magic) {
        return MartialArtsLevel.Sidereal;
      }

      @Override
      public boolean isMagicFavored(Magic magic) {
        return true;
      }
    }));
  }
}