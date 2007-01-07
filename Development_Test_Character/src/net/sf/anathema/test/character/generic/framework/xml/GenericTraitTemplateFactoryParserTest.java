package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.backgrounds.BackgroundRegistry;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactoryParser;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.generic.impl.backgrounds.SimpleBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.dummy.character.template.DummyXmlTemplateRegistry;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GenericTraitTemplateFactoryParserTest extends BasicTestCase {
  String xml = "<traitTemplates >" //$NON-NLS-1$
      + "<backgrounds>" //$NON-NLS-1$
      + "<defaultTrait startValue=\"0\" lowerableState=\"LowerableRegain\">" //$NON-NLS-1$
      + "<limitation type=\"Static\" value=\"5\"/>" //$NON-NLS-1$
      + "<minimum value=\"0\"/>" //$NON-NLS-1$
      + "</defaultTrait>" //$NON-NLS-1$
      + "</backgrounds>" //$NON-NLS-1$
      + "<abilities>" //$NON-NLS-1$
      + "<defaultTrait startValue=\"0\" lowerableState=\"Default\">" //$NON-NLS-1$
      + "<limitation type=\"Essence\"/>" //$NON-NLS-1$
      + "<minimum value=\"0\"/>" //$NON-NLS-1$
      + "</defaultTrait>" //$NON-NLS-1$
      + "</abilities>" //$NON-NLS-1$
      + "<attributes>" //$NON-NLS-1$
      + "<defaultTrait startValue=\"1\" lowerableState=\"Default\">" //$NON-NLS-1$
      + "<limitation type=\"Essence\"/>" //$NON-NLS-1$
      + "<minimum value=\"1\"/>" //$NON-NLS-1$
      + "</defaultTrait>" //$NON-NLS-1$
      + "</attributes>" //$NON-NLS-1$
      + "<virtues>" //$NON-NLS-1$
      + "<defaultTrait startValue=\"1\" lowerableState=\"Default\">" //$NON-NLS-1$
      + "<limitation type=\"Static\" value=\"5\"/>" //$NON-NLS-1$
      + "<minimum value=\"1\"/>" //$NON-NLS-1$
      + "</defaultTrait>" //$NON-NLS-1$
      + "</virtues>" //$NON-NLS-1$
      + "<essence startValue=\"1\" lowerableState=\"Default\">" //$NON-NLS-1$
      + "<limitation type=\"Static\" value=\"1\"/>" //$NON-NLS-1$
      + "<minimum value=\"1\"/>" //$NON-NLS-1$
      + "</essence>" //$NON-NLS-1$
      + "<willpower startValue=\"2\" lowerableState=\"Default\">" //$NON-NLS-1$
      + "<limitation type=\"Static\" value=\"10\"/>" //$NON-NLS-1$
      + "<minimum value=\"2\"/>" //$NON-NLS-1$
      + "</willpower>" //$NON-NLS-1$
      + "</traitTemplates>"; //$NON-NLS-1$
  private DummyXmlTemplateRegistry<GenericTraitTemplateFactory> templateFactoryRegistry;
  private GenericTraitTemplateFactoryParser parser;
  private DummyXmlTemplateRegistry<GenericTraitTemplatePool> templatePoolRegistry;

  @Override
  protected void setUp() throws Exception {
    templateFactoryRegistry = new DummyXmlTemplateRegistry<GenericTraitTemplateFactory>();
    templatePoolRegistry = new DummyXmlTemplateRegistry<GenericTraitTemplatePool>();
    parser = new GenericTraitTemplateFactoryParser(
        templateFactoryRegistry,
        templatePoolRegistry,
        new BackgroundRegistry());
  }

  public void testParseAbility() throws Exception {
    Element traitCollectionElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitTemplateFactory templateFactory = parser.parseTemplate(traitCollectionElement);
    ITraitTemplate abilityTemplate = templateFactory.createAbilityTemplate(AbilityType.Archery);
    AnathemaCharacterAssert.assertEssenceLimitedTraitTemplate(0, 0, 0, abilityTemplate);
  }

  public void testParseAttributes() throws Exception {
    Element traitCollectionElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitTemplateFactory templateFactory = parser.parseTemplate(traitCollectionElement);
    ITraitTemplate attributeTemplate = templateFactory.createAttributeTemplate(AttributeType.Appearance);
    AnathemaCharacterAssert.assertEssenceLimitedTraitTemplate(1, 1, 1, attributeTemplate);
  }

  public void testParseBackground() throws Exception {
    Element traitCollectionElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitTemplateFactory templateFactory = parser.parseTemplate(traitCollectionElement);
    IBackgroundTemplate backgroundType = new SimpleBackgroundTemplate("testBackground"); //$NON-NLS-1$
    ITraitTemplate backgroundTraitTemplate = templateFactory.createBackgroundTemplate(backgroundType);
    AnathemaCharacterAssert.assertStaticSimpleTraitTemplate(0, 0, 0, 5, backgroundTraitTemplate);
  }

  public void testParseEssence() throws Exception {
    Element traitCollectionElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitTemplateFactory templateFactory = parser.parseTemplate(traitCollectionElement);
    ITraitTemplate essenceTemplate = templateFactory.createEssenceTemplate();
    AnathemaCharacterAssert.assertStaticSimpleTraitTemplate(1, 1, 1, 1, essenceTemplate);
  }

  public void testParseVirtues() throws Exception {
    Element traitCollectionElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitTemplateFactory templateFactory = parser.parseTemplate(traitCollectionElement);
    ITraitTemplate virtueTemplate = templateFactory.createVirtueTemplate(VirtueType.Compassion);
    AnathemaCharacterAssert.assertStaticSimpleTraitTemplate(1, 1, 1, 5, virtueTemplate);
  }

  public void testParseWillpower() throws Exception {
    Element traitCollectionElement = DocumentUtilities.read(xml).getRootElement();
    GenericTraitTemplateFactory templateFactory = parser.parseTemplate(traitCollectionElement);
    ITraitTemplate willpowerTemplate = templateFactory.createWillpowerTemplate();
    AnathemaCharacterAssert.assertStaticSimpleTraitTemplate(2, 2, 2, 10, willpowerTemplate);
  }
}