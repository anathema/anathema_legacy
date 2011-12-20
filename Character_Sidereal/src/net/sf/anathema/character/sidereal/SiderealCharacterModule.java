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
import net.sf.anathema.character.generic.impl.backgrounds.EditionSpecificTemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.simple.ISimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.sidereal.additionalrules.AdditionalSiderealRules;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeModelFactory;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeParser;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeViewFactory;
import net.sf.anathema.character.sidereal.colleges.persistence.SiderealCollegePersisterFactory;
import net.sf.anathema.character.sidereal.flawedfate.SiderealFlawedFateModelFactory;
import net.sf.anathema.character.sidereal.flawedfate.SiderealFlawedFateParser;
import net.sf.anathema.character.sidereal.flawedfate.SiderealFlawedFatePersisterFactory;
import net.sf.anathema.character.sidereal.flawedfate.SiderealFlawedFateTemplate;
import net.sf.anathema.character.sidereal.flawedfate.SiderealFlawedFateViewFactory;
import net.sf.anathema.character.sidereal.generic.EssenceAuspicious;
import net.sf.anathema.character.sidereal.generic.PropitiousAlignment;
import net.sf.anathema.character.sidereal.paradox.SiderealParadoxModelFactory;
import net.sf.anathema.character.sidereal.paradox.SiderealParadoxParser;
import net.sf.anathema.character.sidereal.paradox.SiderealParadoxPersisterFactory;
import net.sf.anathema.character.sidereal.paradox.SiderealParadoxTemplate;
import net.sf.anathema.character.sidereal.paradox.SiderealParadoxViewFactory;
import net.sf.anathema.character.sidereal.reporting.content.colleges.SiderealCollageContentFactory;
import net.sf.anathema.character.sidereal.reporting.content.colleges.SiderealCollegeContent;
import net.sf.anathema.character.sidereal.reporting.layout.Extended1stEditionSiderealPartEncoder;
import net.sf.anathema.character.sidereal.reporting.layout.Extended2ndEditionSiderealPartEncoder;
import net.sf.anathema.character.sidereal.reporting.layout.Simple1stEditionSiderealPartEncoder;
import net.sf.anathema.character.sidereal.reporting.layout.Simple2ndEditionSiderealPartEncoder;
import net.sf.anathema.character.sidereal.template.DefaultSiderealTemplate;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.generic.type.CharacterType.SIDEREAL;

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

  private static final TemplateType revisedType = new TemplateType(SIDEREAL, new Identificate("Revised")); //$NON-NLS-1$

  private static final TemplateType dreamsType = new TemplateType(SIDEREAL, new Identificate("Dreams")); //$NON-NLS-1$
  private static final TemplateType revisedDreamsType = new TemplateType(SIDEREAL, new Identificate("RevisedDreams")); //$NON-NLS-1$

  private static final TemplateType[] dreams = {dreamsType, revisedDreamsType};

  public static final String BACKGROUND_ID_ARSENAL = "SiderealDreamsArsenal"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_COMMAND = "SiderealDreamsCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HENCHMEN = "SiderealDreamsHenchmen"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_PANOPLY = "SiderealDreamsPanoply"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_REPUTATION = "SiderealDreamsReputation"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_RETAINERS = "SiderealDreamsRetainers"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WEALTH = "SiderealDreamsWealth"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCasteCollectionRegistry().register(SIDEREAL, new CasteCollection(SiderealCaste.values()));
    characterGenerics.getAdditionalTemplateParserRegistry().register(SiderealCollegeTemplate.ID, new SiderealCollegeParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(SiderealFlawedFateTemplate.ID, new SiderealFlawedFateParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(SiderealParadoxTemplate.ID, new SiderealParadoxParser());
    characterGenerics.getGenericCharmStatsRegistry().register(SIDEREAL, new IMagicStats[]{new FirstExcellency(SIDEREAL,
      ExaltedSourceBook.Sidereals2nd, "1 m per die"), //$NON-NLS-1$
                                                                                          new SecondExcellency(SIDEREAL,
                                                                                            ExaltedSourceBook.Sidereals2nd),
                                                                                          new ThirdExcellency(SIDEREAL, "3 m",
                                                                                            ExaltedSourceBook.Sidereals2nd), //$NON-NLS-1$
                                                                                          new EssenceAuspicious(), new PropitiousAlignment()});
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    CharmCache charmProvider = CharmCache.getInstance();
    characterGenerics.getTemplateRegistry().register(new DefaultSiderealTemplate(charmProvider, new AdditionalSiderealRules()));
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
    ITemplateType[] defaultTemplateType = new ITemplateType[]{DefaultSiderealTemplate.TEMPLATE_TYPE, revisedType};
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_ACQUAINTANCES, SIDEREAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, SIDEREAL));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_CELESTIAL_MANSE, defaultTemplateType));
    backgroundRegistry.add(new EditionSpecificCharacterTypeBackgroundTemplate(BACKGROUND_ID_FAVOR, SIDEREAL, ExaltedEdition.FirstEdition));
    backgroundRegistry.add(new EditionSpecificCharacterTypeBackgroundTemplate(BACKGROUND_ID_HEAVENLY_FAVOR, SIDEREAL, ExaltedEdition.FirstEdition));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SALARY, SIDEREAL));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SAVANT, defaultTemplateType, LowerableState.Default));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SIFU, defaultTemplateType));

    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_ARSENAL, dreams, ExaltedEdition.SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_COMMAND, dreams, ExaltedEdition.SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_HENCHMEN, dreams, ExaltedEdition.SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_PANOPLY, dreams, ExaltedEdition.SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_REPUTATION, dreams, ExaltedEdition.SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_RETAINERS, dreams, ExaltedEdition.SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_SAVANT, dreams, ExaltedEdition.SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_SIFU, dreams, ExaltedEdition.SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_WEALTH, dreams, ExaltedEdition.SecondEdition));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerSiderealColleges(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerFlawedFate(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerParadox(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }

  private void registerSiderealColleges(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry, IRegistry<String,
    IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = SiderealCollegeTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new SiderealCollegeModelFactory());
    additionalViewFactoryRegistry.register(templateId, new SiderealCollegeViewFactory());
    persisterFactory.register(templateId, new SiderealCollegePersisterFactory());
  }

  private void registerFlawedFate(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry, IRegistry<String,
    IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = SiderealFlawedFateTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new SiderealFlawedFateModelFactory());
    additionalViewFactoryRegistry.register(templateId, new SiderealFlawedFateViewFactory());
    persisterFactory.register(templateId, new SiderealFlawedFatePersisterFactory());
  }

  private void registerParadox(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry, IRegistry<String,
    IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = SiderealParadoxTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new SiderealParadoxModelFactory());
    additionalViewFactoryRegistry.register(templateId, new SiderealParadoxViewFactory());
    persisterFactory.register(templateId, new SiderealParadoxPersisterFactory());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    moduleObject.getReportContentRegistry().addFactory(SiderealCollegeContent.class, new SiderealCollageContentFactory(resources));
    registerSimpleReporting(resources, moduleObject.getSimpleEncodingRegistry());
    registerExtendedReporting(resources, moduleObject.getExtendedEncodingRegistry());
  }

  private void registerSimpleReporting(IResources resources, SimpleEncodingRegistry registry) {
    registry.setPartEncoder(SIDEREAL, ExaltedEdition.FirstEdition, new Simple1stEditionSiderealPartEncoder(resources, registry, ESSENCE_MAX));
    registry.setPartEncoder(SIDEREAL, ExaltedEdition.SecondEdition, new Simple2ndEditionSiderealPartEncoder(resources, registry, ESSENCE_MAX));
  }

  private void registerExtendedReporting(IResources resources, ExtendedEncodingRegistry registry) {
    registry.setPartEncoder(SIDEREAL, ExaltedEdition.FirstEdition, new Extended1stEditionSiderealPartEncoder(resources, registry, ESSENCE_MAX));
    registry.setPartEncoder(SIDEREAL, ExaltedEdition.SecondEdition, new Extended2ndEditionSiderealPartEncoder(resources, registry, ESSENCE_MAX));
  }
}
