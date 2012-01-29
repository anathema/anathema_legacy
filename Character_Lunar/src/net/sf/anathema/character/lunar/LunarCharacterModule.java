package net.sf.anathema.character.lunar;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.magic.ThirdExcellency;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.EditionSpecificBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.EditionSpecificCharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.EditionSpecificTemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.lunar.beastform.BeastformModelFactory;
import net.sf.anathema.character.lunar.beastform.BeastformPersisterFactory;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.BeastformViewFactory;
import net.sf.anathema.character.lunar.caste.LunarCaste;
import net.sf.anathema.character.lunar.generic.FlawlessFocus;
import net.sf.anathema.character.lunar.generic.ImpossibleImprovement;
import net.sf.anathema.character.lunar.generic.InstinctiveUnity;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodFactory;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodPersisterFactory;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodViewFactory;
import net.sf.anathema.character.lunar.renown.RenownFactory;
import net.sf.anathema.character.lunar.renown.RenownPersisterFactory;
import net.sf.anathema.character.lunar.renown.RenownTemplate;
import net.sf.anathema.character.lunar.renown.RenownViewFactory;
import net.sf.anathema.character.lunar.reporting.Extended1stEditionLunarPartEncoder;
import net.sf.anathema.character.lunar.reporting.Extended2ndEditionLunarPartEncoder;
import net.sf.anathema.character.lunar.reporting.content.GiftContent;
import net.sf.anathema.character.lunar.reporting.content.GiftContentFactory;
import net.sf.anathema.character.lunar.reporting.content.equipment.LunarArmourContent;
import net.sf.anathema.character.lunar.reporting.content.equipment.LunarArmourContentFactory;
import net.sf.anathema.character.lunar.reporting.content.equipment.LunarWeaponryContent;
import net.sf.anathema.character.lunar.reporting.content.equipment.LunarWeaponryContentFactory;
import net.sf.anathema.character.lunar.reporting.content.knacks.KnackContent;
import net.sf.anathema.character.lunar.reporting.content.knacks.KnackContentFactory;
import net.sf.anathema.character.lunar.reporting.layout.Lunar2ndEditionAdditionalPageFactory;
import net.sf.anathema.character.lunar.reporting.layout.LunarBeastform1stEditionPageFactory;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawModelFactory;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawPersisterFactory;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawViewFactory;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.FirstEdition;
import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.LUNAR;

@CharacterModule
public class LunarCharacterModule extends NullObjectCharacterModuleAdapter {

  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;
  public static final String BACKGROUND_ID_HEARTS_BLOOD = "HeartsBlood"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HEARTS_BLOOD_HUMAN = "HeartsBloodHuman"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_RENOWN = "Renown"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_REPUTATION = "Reputation"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SOLAR_BOND = "SolarBond"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_TATTOO_ARTIFACT = "TattooArtifact"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_TABOO = "Taboo"; //$NON-NLS-1$
  public static final IBackgroundTemplate RENOWN_BACKGROUND_TYPE = new CharacterTypeBackgroundTemplate(BACKGROUND_ID_RENOWN, LUNAR);

  private static final TemplateType castelessType = new TemplateType(LUNAR, new Identificate("Casteless")); //$NON-NLS-1$
  private static final TemplateType revisedCastelessType = new TemplateType(LUNAR, new Identificate("CastelessRevised")); //$NON-NLS-1$
  private static final TemplateType dreamsType = new TemplateType(LUNAR, new Identificate("Dreams")); //$NON-NLS-1$
  private static final TemplateType revisedDreamsType = new TemplateType(LUNAR, new Identificate("DreamsRevised")); //$NON-NLS-1$

  private static final TemplateType[] dreams = {dreamsType, revisedDreamsType};

  public static final String BACKGROUND_ID_ARSENAL = "LunarDreamsArsenal"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_COMMAND = "LunarDreamsCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CONNECTIONS = "LunarDreamsConnections"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HENCHMEN = "LunarDreamsHenchmen"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_PANOPLY = "LunarDreamsPanoply"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_RETAINERS = "LunarDreamsRetainers"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SALARY = "LunarDreamsSalary"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SAVANT = "LunarDreamsSavant"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SIFU = "LunarDreamsSifu"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WEALTH = "LunarDreamsWealth"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalTemplateParserRegistry().register(BeastformTemplate.TEMPLATE_ID, new LunarBeastformParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(HeartsBloodTemplate.TEMPLATE_ID, new LunarHeartsBloodParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(RenownTemplate.TEMPLATE_ID, new LunarRenownParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(LunarVirtueFlawTemplate.TEMPLATE_ID, new LunarVirtueFlawParser());

    Map<IExaltedEdition, ICasteType[]> editionMap = new HashMap<IExaltedEdition, ICasteType[]>();
    editionMap.put(FirstEdition, LunarCaste.getModernValues());
    editionMap.put(SecondEdition, LunarCaste.values());
    Map<ITemplateType, ICasteType[]> templateMap = new HashMap<ITemplateType, ICasteType[]>();
    templateMap.put(castelessType, new ICasteType[]{});
    templateMap.put(revisedCastelessType, new ICasteType[]{});
    templateMap.put(dreamsType, LunarCaste.getDreamsValues());
    templateMap.put(revisedDreamsType, LunarCaste.getDreamsValues());
    characterGenerics.getCasteCollectionRegistry().register(LUNAR, new CasteCollection(LunarCaste.values(), editionMap, templateMap));
    characterGenerics.getGenericCharmStatsRegistry()
            .register(LUNAR, new IMagicStats[]{new FirstExcellency(LUNAR, ExaltedSourceBook.Lunars2nd, "1 m per die"), //$NON-NLS-1$
                    new SecondExcellency(LUNAR, ExaltedSourceBook.Lunars2nd), new ThirdExcellency(LUNAR, "4 m", ExaltedSourceBook.Lunars2nd), //$NON-NLS-1$
                    new InstinctiveUnity(), new FlawlessFocus(), new ImpossibleImprovement()});
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_HEARTS_BLOOD, LUNAR));
    backgroundRegistry.add(new EditionSpecificCharacterTypeBackgroundTemplate(BACKGROUND_ID_HEARTS_BLOOD_HUMAN, LUNAR, FirstEdition));
    backgroundRegistry.add(new EditionSpecificBackgroundTemplate(BACKGROUND_ID_RENOWN, FirstEdition));

    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SOLAR_BOND, LUNAR));
    backgroundRegistry.add(new EditionSpecificCharacterTypeBackgroundTemplate(BACKGROUND_ID_REPUTATION, LUNAR, SecondEdition));
    backgroundRegistry.add(
            new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_TATTOO_ARTIFACT, new ITemplateType[]{castelessType, revisedCastelessType},
                    SecondEdition));
    backgroundRegistry.add(
            new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_TABOO, new ITemplateType[]{castelessType, revisedCastelessType},
                    SecondEdition));

    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_ARSENAL, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_COMMAND, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_HENCHMEN, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_PANOPLY, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_RETAINERS, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_SALARY, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_SAVANT, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_SIFU, dreams, SecondEdition));
    backgroundRegistry.add(new EditionSpecificTemplateTypeBackgroundTemplate(BACKGROUND_ID_WEALTH, dreams, SecondEdition));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Lunar.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Lunar2ndCasteless.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndSilverPact.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndDreams.template");

    registerParsedTemplate(characterGenerics, "template/Lunar2ndCastelessRevised.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndSilverPactRevised.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndDreamsRevised.template");
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerBeastform(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerHeartsblood(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerRenown(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerVirtueFlaw(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }

  private void registerVirtueFlaw(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                                  IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = LunarVirtueFlawTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new LunarVirtueFlawModelFactory());
    additionalViewFactoryRegistry.register(templateId, new LunarVirtueFlawViewFactory());
    persisterFactory.register(templateId, new LunarVirtueFlawPersisterFactory());

  }

  private void registerRenown(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                              IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = RenownTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new RenownFactory());
    additionalViewFactoryRegistry.register(templateId, new RenownViewFactory());
    persisterFactory.register(templateId, new RenownPersisterFactory());
  }

  private void registerHeartsblood(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                                   IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = HeartsBloodTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new HeartsBloodFactory());
    additionalViewFactoryRegistry.register(templateId, new HeartsBloodViewFactory());
    persisterFactory.register(templateId, new HeartsBloodPersisterFactory());
  }

  private void registerBeastform(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                                 IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = BeastformTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new BeastformModelFactory());
    additionalViewFactoryRegistry.register(templateId, new BeastformViewFactory());
    persisterFactory.register(templateId, new BeastformPersisterFactory());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    registerContent(resources, moduleObject.getContentRegistry());
    registerExtendedReporting(resources, moduleObject.getExtendedEncodingRegistry(), moduleObject.getEncoderRegistry());
    moduleObject.getAdditionalPageRegistry().add(new LunarBeastform1stEditionPageFactory());
    moduleObject.getAdditionalPageRegistry().add(new Lunar2ndEditionAdditionalPageFactory());
  }

  private void registerContent(IResources resources, ReportContentRegistry contentRegistry) {
    contentRegistry.addFactory(KnackContent.class, new KnackContentFactory(resources));
    contentRegistry.addFactory(LunarWeaponryContent.class, new LunarWeaponryContentFactory(resources));
    contentRegistry.addFactory(LunarArmourContent.class, new LunarArmourContentFactory(resources));
    contentRegistry.addFactory(GiftContent.class, new GiftContentFactory(resources));
  }

  private void registerExtendedReporting(IResources resources, ExtendedEncodingRegistry registry, EncoderRegistry encoderRegistry) {
    registry
            .setPartEncoder(LUNAR, FirstEdition, new Extended1stEditionLunarPartEncoder(encoderRegistry, resources, registry.getBaseFont(), ESSENCE_MAX));
    registry.setPartEncoder(LUNAR, SecondEdition, new Extended2ndEditionLunarPartEncoder(encoderRegistry, resources, registry, ESSENCE_MAX));
  }
}
