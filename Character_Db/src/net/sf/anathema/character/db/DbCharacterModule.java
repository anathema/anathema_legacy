package net.sf.anathema.character.db;

import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.db.magic.TerrestrialReinforcement;
import net.sf.anathema.character.db.reporting.FirstEditionDbPartEncoder;
import net.sf.anathema.character.db.template.IDbSpecialCharms;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.magic.ThirdExcellency;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
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

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getGenericCharmStatsRegistry().register(
        CharacterType.DB,
        new IMagicStats[] { new FirstExcellency("1 m per 2 dice"), //$NON-NLS-1$
            new SecondExcellency(),
            new ThirdExcellency("3 m"), //$NON-NLS-1$
            new TerrestrialReinforcement() });
    characterGenerics.getCharmProvider().setSpecialCharms(
        CharacterType.DB,
        ExaltedEdition.FirstEdition,
        IDbSpecialCharms.OX_BODY_TECHNIQUE);
    characterGenerics.getCharmProvider().setSpecialCharms(
        CharacterType.DB,
        ExaltedEdition.SecondEdition,
        IDbSpecialCharms.DRAGON_CLAW_ELEMENTAL_STRIKE);
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
    TemplateTypeBackgroundTemplate sorceryBackground = new TemplateTypeBackgroundTemplate(
        BACKGROUND_ID_SORCERY,
        new ITemplateType[] {
            dynastTemplateType,
            lookshyNativeTemplateType,
            lookshyRealmTemplateType,
            lookshyOutcasteTemplateType,
            ketherRockTemplateType,
            tabernacleTemplateType },
        LowerableState.Immutable);
    backgroundRegistry.add(sorceryBackground);
  }

  private void addLookshyBackgrounds(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    ITemplateType[] lookshyTemplateTypes = new ITemplateType[] {
        lookshyNativeTemplateType,
        lookshyRealmTemplateType,
        lookshyOutcasteTemplateType };
    ITemplateType[] retainerTemplateTypes = new ITemplateType[] {
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
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    IPdfPartEncoder firstEditionDbPartEncoder = new FirstEditionDbPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.DB, ExaltedEdition.FirstEdition, firstEditionDbPartEncoder);
  }
}