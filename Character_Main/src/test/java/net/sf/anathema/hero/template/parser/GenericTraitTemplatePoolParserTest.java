package net.sf.anathema.hero.template.parser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.lists.AllAbilityTraitTypeList;
import net.sf.anathema.character.main.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.main.xml.trait.pool.GenericTraitTemplatePoolParser;
import net.sf.anathema.hero.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

public class GenericTraitTemplatePoolParserTest extends TestCase {
  private DummyXmlTemplateRegistry<GenericTraitTemplatePool> templateRegistry;
  private GenericTraitTemplatePoolParser abilityParser;

  @Override
  protected void setUp() throws Exception {
    templateRegistry = new DummyXmlTemplateRegistry<>();
    abilityParser = new GenericTraitTemplatePoolParser(templateRegistry, AllAbilityTraitTypeList.getInstance());
  }

  public void testParseDefault() throws Exception {
    String xml =
            "<backgrounds>" + "<defaultTrait startValue=\"0\" lowerableState=\"LowerableRegain\">" + "<limitation type=\"Static\" value=\"5\"/>" +
            "<minimum value=\"0\"/>" + "</defaultTrait>" + "</backgrounds>";
    Element traitCollectionElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitTemplatePool pool = abilityParser.parseTemplate(traitCollectionElement);
    ITraitTemplate traitTemplate = pool.getTemplate(AbilityType.Archery);
    assertStaticTraitTemplate(0, 0, 0, 5, traitTemplate);
  }

  public void testParseSpecialTemplate() throws Exception {
    String specialXml = "<root>" + "<defaultTrait startValue=\"0\" lowerableState=\"Default\">" + "<limitation type=\"Static\" value=\"5\"/>" +
                        "<minimum value=\"0\"/>" + "</defaultTrait>" +
                        "<specialTrait id=\"Sail\" startValue=\"2\" zeroLevel=\"0\" lowerableState=\"Default\">" +
                        "<limitation type=\"Static\" value=\"7\"/>" + "<minimum value=\"2\"/>" + "</specialTrait>" + "</root>";
    Element traitCollectionElement = DocumentUtilities.read(specialXml).getRootElement();
    GenericTraitTemplatePool pool = abilityParser.parseTemplate(traitCollectionElement);
    ITraitTemplate traitTemplate = pool.getTemplate(AbilityType.Archery);
    assertStaticTraitTemplate(0, 0, 0, 5, traitTemplate);
    ITraitTemplate specialTraitTemplate = pool.getTemplate(AbilityType.Sail);
    assertStaticTraitTemplate(2, 0, 2, 7, specialTraitTemplate);
  }

  private void assertStaticTraitTemplate(int startValue, int zeroLevelValue, int minimalValue, int maximalValue, ITraitTemplate traitTemplate) {
    assertEquals(startValue, traitTemplate.getStartValue());
    assertEquals(zeroLevelValue, traitTemplate.getZeroLevelValue());
    assertEquals(minimalValue, traitTemplate.getMinimumValue(null));
    assertEquals(maximalValue, ((StaticTraitLimitation) traitTemplate.getLimitation()).getStaticLimit());
  }

  public void testAlternateMinimumAbilityXml() throws Exception {
    String xml = "<root>" + "       <alternateMinimumTraits count=\"1\" value=\"1\">" +
                 "           <trait id=\"Archery\" startValue=\"0\" lowerableState=\"Default\" zeroLevel=\"0\">" +
                 "               <limitation type=\"Essence\"/>" + "               <minimum value=\"0\"/>" + "           </trait>" +
                 "           <trait id=\"MartialArts\" startValue=\"1\" lowerableState=\"Default\" zeroLevel=\"0\">" +
                 "               <limitation type=\"Essence\"/>" + "               <minimum value=\"0\"/>" + "           </trait>" +
                 "       </alternateMinimumTraits>" + "   </root>";
    GenericTraitTemplatePool pool = abilityParser.parseTemplate(DocumentUtilities.read(xml).getRootElement());
    ITraitTemplate brawlTemplate = pool.getTemplate(AbilityType.MartialArts);
    assertEquals(1, brawlTemplate.getStartValue());
  }
}