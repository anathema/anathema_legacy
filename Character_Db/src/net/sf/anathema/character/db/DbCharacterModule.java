package net.sf.anathema.character.db;

import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.db.magic.TerrestrialReinforcement;
import net.sf.anathema.character.db.reporting.FirstEditionDbPartEncoder;
import net.sf.anathema.character.db.reporting.SecondEditionDbPartEncoder;
import net.sf.anathema.character.db.template.IDbSpecialCharms;
import net.sf.anathema.character.db.virtueflaw.DbVirtueFlawModelFactory;
import net.sf.anathema.character.db.virtueflaw.DbVirtueFlawParser;
import net.sf.anathema.character.db.virtueflaw.DbVirtueFlawTemplate;
import net.sf.anathema.character.db.virtueflaw.DbVirtueFlawViewFactory;
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
import net.sf.anathema.character.generic.impl.backgrounds.EditionSpecificTemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.virtueflaw.persistence.DefaultVirtueFlawPersisterFactory;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class DbCharacterModule extends NullObjectCharacterModuleAdapter {

  private static final int ESSENCE_MAX = EssenceTemplate.DB_ESSENCE_MAX;
  public static final String BACKGROUND_ID_ARSENAL = "Arsenal"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_BREEDING = "Breeding"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_FAMILY = "Family"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_COMMAND = "Command"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CONNECTIONS = "Connections"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HENCHMEN = "Henchmen"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_ILLUMINATION = "Illumination"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_REPUTATION = "Reputation"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_RETAINERS = "Retainers"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SORCERY = "Sorcery"; //$NON-NLS-1$
  private static final TemplateType dynastTemplateType = new TemplateType(CharacterType.DB);
  private static final TemplateType tabernacleTemplateType = new TemplateType(CharacterType.DB, new Identificate(
      "SequesteredTabernacleSubtype")); //$NON-NLS-1$
  private static final TemplateType ketherRockTemplateType = new TemplateType(CharacterType.DB, new Identificate(
      "KetherRockSubtype")); //$NON-NLS-1$
  private static final TemplateType pirateRealmTemplateType = new TemplateType(CharacterType.DB, new Identificate(
      "PirateRealmSubtype")); //$NON-NLS-1$
  private static final TemplateType pirateOutcasteTemplateType = new TemplateType(CharacterType.DB, new Identificate(
      "PirateOutcasteSubtype")); //$NON-NLS-1$
  private static final TemplateType patricianOutcasteTemplateType = new TemplateType(
      CharacterType.DB,
      new Identificate("PatricianOutcasteSubtype")); //$NON-NLS-1$
  private static final TemplateType lowerCasteOutcasteTemplateType = new TemplateType(
      CharacterType.DB,
      new Identificate("LowerClassOutcasteSubtype")); //$NON-NLS-1$
  private static final TemplateType lookshyNativeTemplateType = new TemplateType(CharacterType.DB, new Identificate(
      "LookshySubtype")); //$NON-NLS-1$
  private static final TemplateType lookshyOutcasteTemplateType = new TemplateType(CharacterType.DB, new Identificate(
      "LookshyOutcasteSubtype")); //$NON-NLS-1$
  private static final TemplateType lookshyRealmTemplateType = new TemplateType(CharacterType.DB, new Identificate(
      "LookshyRealmSubtype")); //$NON-NLS-1$
  private static final TemplateType cherakiTemplateType = new TemplateType(CharacterType.DB, new Identificate("Cherak")); //$NON-NLS-1$
  private static final TemplateType immaculateTemplateType = new TemplateType(CharacterType.DB, new Identificate(
      "ImmaculateSubtype")); //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getGenericCharmStatsRegistry().register(
        CharacterType.DB,
        new IMagicStats[] {
            new FirstExcellency(CharacterType.DB, ExaltedSourceBook.DragonBlooded2nd, "1 m per 2 dice"), //$NON-NLS-1$
            new SecondExcellency(CharacterType.DB, ExaltedSourceBook.DragonBlooded2nd),
            new ThirdExcellency(CharacterType.DB, "3 m", ExaltedSourceBook.DragonBlooded2nd), //$NON-NLS-1$
            new TerrestrialReinforcement() });
    characterGenerics.getCharmProvider().setSpecialCharms(
        CharacterType.DB,
        ExaltedEdition.FirstEdition,
        IDbSpecialCharms.OX_BODY_TECHNIQUE);
    characterGenerics.getCharmProvider().setSpecialCharms(
        CharacterType.DB,
        ExaltedEdition.SecondEdition,
        IDbSpecialCharms.OX_BODY_TECHNIQUE_SECOND_EDITION,
        IDbSpecialCharms.DRAGON_CLAW_ELEMENTAL_STRIKE,
        IDbSpecialCharms.ELEMENT_PROTECTION_FORM,
        IDbSpecialCharms.ELEMENT_SHELTER_CREATION_TECHNIQUE,
        IDbSpecialCharms.ELEMENTAL_DEFENSE_TECHNIQUE,
        IDbSpecialCharms.EXTENSION_ELEMENT_DRAGONS_BLESSING,
        IDbSpecialCharms.TERRIFYING_ELEMENT_DRAGON_ROAR,
        IDbSpecialCharms.UNASSAILABLE_BODY_OF_ELEMENT_DEFENSE);
    characterGenerics.getAdditionalTemplateParserRegistry().register(
        DbVirtueFlawTemplate.TEMPLATE_ID,
        new DbVirtueFlawParser());
    characterGenerics.getCasteCollectionRegistry().register(CharacterType.DB, new CasteCollection(DBAspect.values()));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/DynasticDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/ImmaculateMonkDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/SequesteredTabernacleDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/KetherRockDb.template"); //$NON-NLS-1$  
    registerParsedTemplate(characterGenerics, "template/LookshyNativeDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/LookshyRealmDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/LookshyOutcasteDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/PatricianOutcasteDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/LowerClassOutcasteDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/ThresholdOutcasteDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/PirateRealmDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/PirateOutcasteDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/DynasticDb2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/ImmaculateMonkDb2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/CherakDb2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/ThresholdOutcasteDb2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/LookshyNativeDb2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/LookshyOutcasteDb2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/LookshyRealmDb2nd.template"); //$NON-NLS-1$
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    IBackgroundTemplate breedingTemplate = new CharacterTypeBackgroundTemplate(
        BACKGROUND_ID_BREEDING,
        CharacterType.DB,
        LowerableState.Immutable);
    backgroundRegistry.add(breedingTemplate);
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_COMMAND, CharacterType.DB));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, CharacterType.DB));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_FAMILY, new ITemplateType[] {
        dynastTemplateType,
        lookshyNativeTemplateType,
        lookshyRealmTemplateType }));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_HENCHMEN, CharacterType.DB));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_REPUTATION, CharacterType.DB));
    addLookshyBackgrounds(backgroundRegistry);
    addCultBackgrounds(backgroundRegistry);
    addSorcery(backgroundRegistry);
  }

  private void addCultBackgrounds(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    ITemplateType[] cultTemplates = new ITemplateType[] { ketherRockTemplateType, tabernacleTemplateType };
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ILLUMINATION, cultTemplates));
  }

  private void addSorcery(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    TemplateTypeBackgroundTemplate sorceryBackground = new EditionSpecificTemplateTypeBackgroundTemplate(
        BACKGROUND_ID_SORCERY,
        new ITemplateType[] {
            dynastTemplateType,
            lookshyNativeTemplateType,
            lookshyRealmTemplateType,
            lookshyOutcasteTemplateType,
            ketherRockTemplateType,
            tabernacleTemplateType },
        ExaltedEdition.FirstEdition,
        LowerableState.Immutable);
    backgroundRegistry.add(sorceryBackground);
  }

  private void addLookshyBackgrounds(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    ITemplateType[] lookshyTemplateTypes = new ITemplateType[] {
        lookshyNativeTemplateType,
        lookshyRealmTemplateType,
        lookshyOutcasteTemplateType };
    ITemplateType[] retainerTemplateTypes = new ITemplateType[] {
        dynastTemplateType,
        immaculateTemplateType,
        cherakiTemplateType,
        lookshyNativeTemplateType,
        lookshyRealmTemplateType,
        lookshyOutcasteTemplateType,
        pirateOutcasteTemplateType,
        pirateRealmTemplateType,
        patricianOutcasteTemplateType,
        lowerCasteOutcasteTemplateType };
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ARSENAL, lookshyTemplateTypes));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_RETAINERS, retainerTemplateTypes));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = DbVirtueFlawTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new DbVirtueFlawModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new DbVirtueFlawViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new DefaultVirtueFlawPersisterFactory());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    IPdfPartEncoder firstEditionDbPartEncoder = new FirstEditionDbPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.DB, ExaltedEdition.FirstEdition, firstEditionDbPartEncoder);
    IPdfPartEncoder secondEditionDbPartEncoder = new SecondEditionDbPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.DB, ExaltedEdition.SecondEdition, secondEditionDbPartEncoder);
  }
}