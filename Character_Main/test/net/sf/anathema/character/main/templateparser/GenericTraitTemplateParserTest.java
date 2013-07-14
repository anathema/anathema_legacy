package net.sf.anathema.character.main.templateparser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.traits.LowerableState;
import net.sf.anathema.character.main.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.main.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateParser;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

public class GenericTraitTemplateParserTest extends TestCase {

  private static final String minimalBasicAttributes = "startValue=\"1\" lowerableState=\"Default\"";
  private static final String minimalMinimumElement = "<minimum value=\"0\" />";
  private static final String minimalLimitationElement = "<limitation type=\"Static\" value=\"5\" />";
  private static final String minimalOpeningTag = createOpeningTag(minimalBasicAttributes);
  private static final String closingTag = "</testTrait>";

  private static String createMinimalXmlForBasicAttributes(String attributes) {
    return createOpeningTag(attributes) + minimalMinimumElement + minimalLimitationElement + closingTag;
  }

  private static String createOpeningTag(String attributes) {
    return "<testTrait " + attributes + ">";
  }

  private static GenericTraitTemplate parseTemplate(String xml) throws AnathemaException {
    Element element = DocumentUtilities.read(xml).getRootElement();
    return (GenericTraitTemplate) GenericTraitTemplateParser.parseTraitTemplate(element);
  }

  public void testBasicTraitTemplateProperties() throws Exception {
    String xml = createMinimalXmlForBasicAttributes("startValue=\"1\" zeroLevel=\"0\" lowerableState=\"Default\" isRequiredFavored=\"true\"");
    GenericTraitTemplate template = parseTemplate(xml);
    assertEquals(1, template.getStartValue());
    assertEquals(0, template.getZeroLevelValue());
    assertEquals(LowerableState.Default, template.getLowerableState());
    assertTrue(template.isRequiredFavored());
  }

  public void testUngivenZeroLevelDefaultsToStartValue() throws Exception {
    String xml = createMinimalXmlForBasicAttributes(minimalBasicAttributes);
    GenericTraitTemplate template = parseTemplate(xml);
    assertEquals(1, template.getStartValue());
    assertEquals(1, template.getZeroLevelValue());
  }

  public void testUngivenRequirementStatusDefaultsToFalse() throws Exception {
    String xml = createMinimalXmlForBasicAttributes(minimalBasicAttributes);
    GenericTraitTemplate template = parseTemplate(xml);
    assertFalse(template.isRequiredFavored());
  }

  public void testStaticTraitLimitation() throws Exception {
    String limitationTag = "<limitation type=\"Static\" value=\"5\" />";
    String xml = minimalOpeningTag + minimalMinimumElement + limitationTag + closingTag;
    GenericTraitTemplate template = parseTemplate(xml);
    assertEquals(new StaticTraitLimitation(5), template.getLimitation());
  }

  public void testEssenceTraitLimitation() throws Exception {
    String limitationTag = "<limitation type=\"Essence\" />";
    String xml = minimalOpeningTag + minimalMinimumElement + limitationTag + closingTag;
    GenericTraitTemplate template = parseTemplate(xml);
    assertTrue(template.getLimitation() instanceof EssenceBasedLimitation);
  }
}