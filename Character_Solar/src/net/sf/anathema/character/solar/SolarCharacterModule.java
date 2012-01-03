package net.sf.anathema.character.solar;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.magic.ThirdExcellency;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.EditionSpecificTemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.solar.caste.SolarCaste;
import net.sf.anathema.character.solar.generic.DivineTranscendenceOf;
import net.sf.anathema.character.solar.generic.EssenceFlow;
import net.sf.anathema.character.solar.generic.InfiniteMastery;
import net.sf.anathema.character.solar.generic.SupremePerfectionOf;
import net.sf.anathema.character.solar.reporting.Extended1stEditionSolarPartEncoder;
import net.sf.anathema.character.solar.reporting.Extended2ndSolarPartEncoder;
import net.sf.anathema.character.solar.reporting.Simple1stEditionSolarPartEncoder;
import net.sf.anathema.character.solar.reporting.Simple2ndSolarPartEncoder;
import net.sf.anathema.character.solar.reporting.SolarVirtueFlawContent;
import net.sf.anathema.character.solar.reporting.SolarVirtueFlawContentFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawModelFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawPersisterFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawViewFactory;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.FirstEdition;
import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.SOLAR;

public class SolarCharacterModule extends NullObjectCharacterModuleAdapter {

  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;

  @SuppressWarnings("unused")
  private static final TemplateType solarTemplateType = new TemplateType(SOLAR);

  @SuppressWarnings("unused")
  private static final TemplateType solarRevisedTemplateType = new TemplateType(SOLAR, new Identificate("RevisedSolarSubtype")); //$NON-NLS-1$

  private static final TemplateType dreamsSolarTemplateType = new TemplateType(SOLAR, new Identificate("Dreams")); //$NON-NLS-1$
  private static final TemplateType dreamsSolarRevisedTemplateType = new TemplateType(SOLAR, new Identificate("DreamsRevised")); //$NON-NLS-1$

  private static final TemplateType[] dreams = { dreamsSolarTemplateType, dreamsSolarRevisedTemplateType };

  public static final String BACKGROUND_ID_ARSENAL = "SolarDreamsArsenal"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_COMMAND = "SolarDreamsCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CONNECTIONS = "SolarDreamsConnections"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HENCHMEN = "SolarDreamsHenchmen"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_PANOPLY = "SolarDreamsPanoply"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_REPUTATION = "SolarDreamsReputation"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_RETAINERS = "SolarDreamsRetainers"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SALARY = "SolarDreamsSalary"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SAVANT = "SolarDreamsSavant"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SIFU = "SolarDreamsSifu"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WEALTH = "SolarDreamsWealth"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    FirstExcellency firstExcellency = new FirstExcellency(SOLAR, ExaltedSourceBook.SecondEdition, "1 m per die");
    SecondExcellency secondExcellency = new SecondExcellency(SOLAR, ExaltedSourceBook.SecondEdition);
    ThirdExcellency thirdExcellency = new ThirdExcellency(SOLAR, "4 m", ExaltedSourceBook.SecondEdition);
    IRegistry<ICharacterType, IMagicStats[]> genericRegistery = characterGenerics.getGenericCharmStatsRegistry();
    genericRegistery.register(SOLAR,
      new IMagicStats[] { firstExcellency, secondExcellency, thirdExcellency, new InfiniteMastery(), new EssenceFlow(),
        new DivineTranscendenceOf(), new SupremePerfectionOf() });

    characterGenerics.getAdditionalTemplateParserRegistry().register(SolarVirtueFlawTemplate.ID, new SolarVirtueFlawParser());
    characterGenerics.getCasteCollectionRegistry().register(SOLAR, new CasteCollection(SolarCaste.values()));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsDawnSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsZenithSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsTwilightSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsNightSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsEclipseSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsDawnSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsZenithSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsTwilightSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsNightSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsEclipseSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2ndDreams.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2ndDreamsRevised.template"); //$NON-NLS-1$
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_ARSENAL, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_COMMAND, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_HENCHMEN, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_PANOPLY, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_REPUTATION, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_RETAINERS, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_SALARY, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_SAVANT, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_SIFU, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_WEALTH, dreams, SecondEdition));
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    moduleObject.getContentRegistry().addFactory(SolarVirtueFlawContent.class, new SolarVirtueFlawContentFactory(resources));
    registerSimpleEncoders(resources, moduleObject);
    registerExtendedEncoders(resources, moduleObject);
  }

  private void registerSimpleEncoders(IResources resources, CharacterReportingModuleObject moduleObject) {
    SimpleEncodingRegistry registry = moduleObject.getSimpleEncodingRegistry();
    registry.setPartEncoder(SOLAR, SecondEdition, new Simple2ndSolarPartEncoder(resources));
    registry.setPartEncoder(SOLAR, FirstEdition, new Simple1stEditionSolarPartEncoder(resources));
  }

  private void registerExtendedEncoders(IResources resources, CharacterReportingModuleObject moduleObject) {
    ExtendedEncodingRegistry registry = moduleObject.getExtendedEncodingRegistry();
    registry.setPartEncoder(SOLAR, SecondEdition, new Extended2ndSolarPartEncoder(resources, registry, ESSENCE_MAX));
    registry.setPartEncoder(SOLAR, FirstEdition, new Extended1stEditionSolarPartEncoder(resources, registry.getBaseFont(), ESSENCE_MAX));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = SolarVirtueFlawTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new SolarVirtueFlawModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new SolarVirtueFlawViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new SolarVirtueFlawPersisterFactory());
  }
}
