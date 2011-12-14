package net.sf.anathema.character.generic.framework.xml;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateParser;
import net.sf.anathema.character.generic.impl.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

public class GenericTraitTemplateParserTest extends TestCase {

  private static final String minimalBasicAttributes = "startValue=\"1\" lowerableState=\"Default\""; //$NON-NLS-1$
  private static final String minimalMinimumElement = "<minimum value=\"0\" />"; //$NON-NLS-1$;
  private static final String minimalLimitationElement = "<limitation type=\"Static\" value=\"5\" />"; //$NON-NLS-1$
  private static final String minimalOpeningTag = createOpeningTag(minimalBasicAttributes);
  private static final String closingTag = "</testTrait>"; //$NON-NLS-1$

  private static String createMinimalXmlForBasicAttributes(String attributes) {
    return createOpeningTag(attributes) + minimalMinimumElement + minimalLimitationElement + closingTag;
  }

  private static String createOpeningTag(String attributes) {
    return "<testTrait " + attributes + ">"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  private static GenericTraitTemplate parseTemplate(String xml) throws AnathemaException {
    Element element = DocumentUtilities.read(xml).getRootElement();
    return (GenericTraitTemplate) GenericTraitTemplateParser.parseTraitTemplate(element);
  }

  public void testBasicTraitTemplateProperties() throws Exception {
    String xml = createMinimalXmlForBasicAttributes("startValue=\"1\" zeroLevel=\"0\" lowerableState=\"Default\" isRequiredFavored=\"true\""); //$NON-NLS-1$
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
    String limitationTag = "<limitation type=\"Static\" value=\"5\" />"; //$NON-NLS-1$
    String xml = minimalOpeningTag + minimalMinimumElement + limitationTag + closingTag;
    GenericTraitTemplate template = parseTemplate(xml);
    assertEquals(new StaticTraitLimitation(5), template.getLimitation());
  }

  public void testEssenceTraitLimitation() throws Exception {
    String limitationTag = "<limitation type=\"Essence\" />"; //$NON-NLS-1$
    String xml = minimalOpeningTag + minimalMinimumElement + limitationTag + closingTag;
    GenericTraitTemplate template = parseTemplate(xml);
    assertTrue(template.getLimitation() instanceof EssenceBasedLimitation);
  }
}