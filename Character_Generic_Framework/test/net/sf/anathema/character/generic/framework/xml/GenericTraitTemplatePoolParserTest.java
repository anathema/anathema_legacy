package net.sf.anathema.character.generic.framework.xml;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePoolParser;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.groups.AllAbilityTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

public class GenericTraitTemplatePoolParserTest extends TestCase {
  private DummyXmlTemplateRegistry<GenericTraitTemplatePool> templateRegistry;
  private GenericTraitTemplatePoolParser abilityParser;

  @Override
  protected void setUp() throws Exception {
    templateRegistry = new DummyXmlTemplateRegistry<GenericTraitTemplatePool>();
    abilityParser = new GenericTraitTemplatePoolParser(templateRegistry, AllAbilityTraitTypeGroup.getInstance());
  }

  public void testParseDefault() throws Exception {
    String xml = "<backgrounds>" //$NON-NLS-1$
        + "<defaultTrait startValue=\"0\" lowerableState=\"LowerableRegain\">" //$NON-NLS-1$
        + "<limitation type=\"Static\" value=\"5\"/>" //$NON-NLS-1$
        + "<minimum value=\"0\"/>" //$NON-NLS-1$
        + "</defaultTrait>" //$NON-NLS-1$
        + "</backgrounds>";//$NON-NLS-1$
    Element traitCollectionElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitTemplatePool pool = abilityParser.parseTemplate(traitCollectionElement);
    ITraitTemplate traitTemplate = pool.getTemplate(AbilityType.Archery);
    assertStaticTraitTemplate(0, 0, 0, 5, traitTemplate);
  }

  public void testParseSpecialTemplate() throws Exception {
    String specialXml = "<root>" //$NON-NLS-1$
        + "<defaultTrait startValue=\"0\" lowerableState=\"Default\">" //$NON-NLS-1$
        + "<limitation type=\"Static\" value=\"5\"/>" //$NON-NLS-1$
        + "<minimum value=\"0\"/>" //$NON-NLS-1$
        + "</defaultTrait>" //$NON-NLS-1$
        + "<specialTrait id=\"Sail\" startValue=\"2\" zeroLevel=\"0\" lowerableState=\"Default\">" //$NON-NLS-1$
        + "<limitation type=\"Static\" value=\"7\"/>" //$NON-NLS-1$
        + "<minimum value=\"2\"/>" //$NON-NLS-1$
        + "</specialTrait>" //$NON-NLS-1$
        + "</root>";//$NON-NLS-1$
    Element traitCollectionElement = DocumentUtilities.read(specialXml).getRootElement();
    GenericTraitTemplatePool pool = abilityParser.parseTemplate(traitCollectionElement);
    ITraitTemplate traitTemplate = pool.getTemplate(AbilityType.Archery);
    assertStaticTraitTemplate(0, 0, 0, 5, traitTemplate);
    ITraitTemplate specialTraitTemplate = pool.getTemplate(AbilityType.Sail);
    assertStaticTraitTemplate(2, 0, 2, 7, specialTraitTemplate);
  }

  private void assertStaticTraitTemplate(
      int startValue,
      int zeroLevelValue,
      int minimalValue,
      int maximalValue,
      ITraitTemplate traitTemplate) {
    assertEquals(startValue, traitTemplate.getStartValue());
    assertEquals(zeroLevelValue, traitTemplate.getZeroLevelValue());
    assertEquals(minimalValue, traitTemplate.getMinimumValue(null));
    assertEquals(maximalValue, ((StaticTraitLimitation) traitTemplate.getLimitation()).getStaticLimit());
  }

  public void testAlternateMinimumAbilityXml() throws Exception {
    String xml = "<root>" //$NON-NLS-1$
        + "       <alternateMinimumTraits count=\"1\" value=\"1\">" //$NON-NLS-1$
        + "           <trait id=\"Archery\" startValue=\"0\" lowerableState=\"Default\" zeroLevel=\"0\">" //$NON-NLS-1$
        + "               <limitation type=\"Essence\"/>" //$NON-NLS-1$
        + "               <minimum value=\"0\"/>" //$NON-NLS-1$
        + "           </trait>" //$NON-NLS-1$
        + "           <trait id=\"Brawl\" startValue=\"1\" lowerableState=\"Default\" zeroLevel=\"0\">" //$NON-NLS-1$
        + "               <limitation type=\"Essence\"/>" //$NON-NLS-1$
        + "               <minimum value=\"0\"/>" //$NON-NLS-1$
        + "           </trait>" //$NON-NLS-1$
        + "       </alternateMinimumTraits>" //$NON-NLS-1$
        + "   </root>"; //$NON-NLS-1$
    GenericTraitTemplatePool pool = abilityParser.parseTemplate(DocumentUtilities.read(xml).getRootElement());
    ITraitTemplate brawlTemplate = pool.getTemplate(AbilityType.Brawl);
    assertEquals(1, brawlTemplate.getStartValue());
  }
}