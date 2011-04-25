package net.sf.anathema.character.sidereal;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.magic.ThirdExcellency;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.EditionSpecificCharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.sidereal.additionalrules.AdditionalSiderealRules;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeModelFactory;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeParser;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeViewFactory;
import net.sf.anathema.character.sidereal.colleges.persistence.SiderealCollegePersisterFactory;
import net.sf.anathema.character.sidereal.generic.EssenceAuspicious;
import net.sf.anathema.character.sidereal.generic.PropitiousAlignment;
import net.sf.anathema.character.sidereal.reporting.FirstEditionSiderealPartEncoder;
import net.sf.anathema.character.sidereal.reporting.SecondEditionSiderealPartEncoder;
import net.sf.anathema.character.sidereal.template.DefaultSiderealTemplate;
import net.sf.anathema.character.sidereal.template.ISiderealSpecialCharms;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class SiderealCharacterModule extends NullObjectCharacterModuleAdapter {
  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;
  public static final String BACKGROUND_ID_ACQUAINTANCES = "Acquaintances"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CONNECTIONS = "Connections"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CELESTIAL_MANSE = "CelestialManse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_FAVOR = "Favor"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HEAVENLY_FAVOR = "HeavenlyFavor"; //$NON-NLS-1$  
  public static final String BACKGROUND_ID_SALARY = "Salary"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SAVANT = "Savant"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SIFU = "Sifu"; //$NON-NLS-1$
  
  private static final TemplateType revisedType = new TemplateType(CharacterType.SIDEREAL, new Identificate(
  "Revised")); //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    ISpecialCharm[] firstEditioncharms = new ISpecialCharm[] {
        ISiderealSpecialCharms.OX_BODY_TECHNIQUE,
        ISiderealSpecialCharms.WORLD_SHAPING_ARTISTIC_VISION };
    ISpecialCharm[] secondEditioncharms = new ISpecialCharm[] {
            ISiderealSpecialCharms.OX_BODY_TECHNIQUE_2ND,
            ISiderealSpecialCharms.WORLD_SHAPING_ARTISTIC_VISION_2ND,
            ISiderealSpecialCharms.MANY_MISSILES_BOW,
            ISiderealSpecialCharms.MASQUE_OF_THE_UNCANNY,
            ISiderealSpecialCharms.MIRROR_SHATTERING_METHOD,
            ISiderealSpecialCharms.WALLS_OF_SALT_AND_ASH};
    characterGenerics.getCharmProvider().setSpecialCharms(CharacterType.SIDEREAL, ExaltedEdition.FirstEdition, firstEditioncharms);
    characterGenerics.getCharmProvider().setSpecialCharms(CharacterType.SIDEREAL, ExaltedEdition.SecondEdition, secondEditioncharms);
    characterGenerics.getCasteCollectionRegistry().register(
        CharacterType.SIDEREAL,
        new CasteCollection(SiderealCaste.values()));
    characterGenerics.getAdditionalTemplateParserRegistry().register(
    		SiderealCollegeTemplate.ID,
            new SiderealCollegeParser());
    characterGenerics.getGenericCharmStatsRegistry().register(
            CharacterType.SIDEREAL,
            new IMagicStats[] { new FirstExcellency(CharacterType.SIDEREAL, ExaltedSourceBook.Sidereals2nd, "1 m per die"), //$NON-NLS-1$
                new SecondExcellency(CharacterType.SIDEREAL, ExaltedSourceBook.Sidereals2nd),
                new ThirdExcellency(CharacterType.SIDEREAL, "3 m", ExaltedSourceBook.Sidereals2nd), //$NON-NLS-1$
                new EssenceAuspicious(),
                new PropitiousAlignment()});
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    CharmCache charmProvider = CharmCache.getInstance();
    characterGenerics.getTemplateRegistry().register(
        new DefaultSiderealTemplate(charmProvider, new AdditionalSiderealRules()));
    registerParsedTemplate(characterGenerics, "template/Sidereal2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/SiderealDreams2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Ronin2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/RevisedSidereal2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/RevisedSiderealDreams2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/RevisedRonin2nd.template"); //$NON-NLS-1$
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    ITemplateType[] defaultTemplateType = new ITemplateType[] { DefaultSiderealTemplate.TEMPLATE_TYPE, revisedType };
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_ACQUAINTANCES, CharacterType.SIDEREAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, CharacterType.SIDEREAL));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_CELESTIAL_MANSE, defaultTemplateType));
    backgroundRegistry.add(new EditionSpecificCharacterTypeBackgroundTemplate(BACKGROUND_ID_FAVOR, CharacterType.SIDEREAL, ExaltedEdition.FirstEdition));
    backgroundRegistry.add(new EditionSpecificCharacterTypeBackgroundTemplate(BACKGROUND_ID_HEAVENLY_FAVOR, CharacterType.SIDEREAL, ExaltedEdition.FirstEdition));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SALARY, defaultTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(
        BACKGROUND_ID_SAVANT,
        defaultTemplateType,
        LowerableState.Default));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SIFU, defaultTemplateType));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerSiderealColleges(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    //registerFlawedFate(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }
  
  private void registerSiderealColleges(
		  IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
	      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
	      IRegistry<String, IAdditionalPersisterFactory> persisterFactory)
  {
	  String templateId = SiderealCollegeTemplate.ID;
	  additionalModelFactoryRegistry.register(templateId, new SiderealCollegeModelFactory());  
	  additionalViewFactoryRegistry.register(templateId, new SiderealCollegeViewFactory());
	  persisterFactory.register(templateId, new SiderealCollegePersisterFactory());
  }
  
  /*private void registerFlawedFate(
		  IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
	      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
	      IRegistry<String, IAdditionalPersisterFactory> persisterFactory)
  {
	  //String templateId = SiderealFlawedFateTemplate.TEMPLATE_ID;
	  //additionalModelFactoryRegistry.register(templateId, new SiderealFlawedFateModelFactory());  
	  //additionalViewFactoryRegistry.register(templateId, new SiderealFlawedFateViewFactory());
	  //persisterFactory.register(templateId, new SiderealFlawedFatePersisterFactory());
  }*/

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    IPdfPartEncoder firstEditionEncoder = new FirstEditionSiderealPartEncoder(resources, registry, ESSENCE_MAX);
    IPdfPartEncoder secondEditionEncoder = new SecondEditionSiderealPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.SIDEREAL, ExaltedEdition.FirstEdition, firstEditionEncoder);
    registry.setPartEncoder(CharacterType.SIDEREAL, ExaltedEdition.SecondEdition, secondEditionEncoder);
  }
}