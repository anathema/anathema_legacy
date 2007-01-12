package net.sf.anathema.character.db;

import net.sf.anathema.character.db.additional.AdditionalCultDbRules;
import net.sf.anathema.character.db.additional.AdditionalDbRules;
import net.sf.anathema.character.db.additional.AdditionalOutcasteDbRules;
import net.sf.anathema.character.db.additional.AdditionalSequesteredTabernacleDbRules;
import net.sf.anathema.character.db.additional.BasicAdditionalLookshyDbRules;
import net.sf.anathema.character.db.additional.NativeLookshyDbRules;
import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.db.reporting.FirstEditionDbPartEncoder;
import net.sf.anathema.character.db.template.IDbSpecialCharms;
import net.sf.anathema.character.db.template.cult.KetherRockDbTemplate;
import net.sf.anathema.character.db.template.cult.SequesteredTabernacleDbTemplate;
import net.sf.anathema.character.db.template.lookshy.LookshyDbTemplate;
import net.sf.anathema.character.db.template.lookshy.LookshyOutcasteDbTemplate;
import net.sf.anathema.character.db.template.lookshy.LookshyRealmDbTemplate;
import net.sf.anathema.character.db.template.outcaste.LowerClassOutcasteDbTemplate;
import net.sf.anathema.character.db.template.outcaste.PatricianOutcasteDBTemplate;
import net.sf.anathema.character.db.template.outcaste.ThresholdOutcasteDbTemplate;
import net.sf.anathema.character.db.template.pirates.PirateOutcasteDbTemplate;
import net.sf.anathema.character.db.template.pirates.PirateRealmDbTemplate;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
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
  private final AdditionalDbRules additionalDbRules = new AdditionalDbRules();
  private final AdditionalDbRules immaculateDbRules = new AdditionalDbRules();
  private final NativeLookshyDbRules nativeLookshyDbRules = new NativeLookshyDbRules();
  private final BasicAdditionalLookshyDbRules realmLookshyDbRules = new BasicAdditionalLookshyDbRules();
  private final AdditionalOutcasteDbRules outcasteDbRules = new AdditionalOutcasteDbRules();
  private final AdditionalCultDbRules cultRules = new AdditionalCultDbRules();
  private final AdditionalSequesteredTabernacleDbRules sequesteredTabernacleRules = new AdditionalSequesteredTabernacleDbRules();

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    ISpecialCharm[] specialCharms = new ISpecialCharm[] { IDbSpecialCharms.OX_BODY_TECHNIQUE };
    characterGenerics.getCharmProvider().setSpecialCharms(CharacterType.DB, ExaltedEdition.FirstEdition, specialCharms);
    characterGenerics.getCasteCollectionRegistry().register(CharacterType.DB, new CasteCollection(DBAspect.values()));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    CharmCache charmProvider = CharmCache.getInstance();
    registerParsedTemplate(characterGenerics, "template/DynasticDb.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/ImmaculateMonkDb.template"); //$NON-NLS-1$
    registerDbTemplate(characterGenerics.getTemplateRegistry(), charmProvider);
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    IBackgroundTemplate breedingTemplate = new CharacterTypeBackgroundTemplate(
        BACKGROUND_ID_BREEDING,
        CharacterType.DB,
        LowerableState.Immutable);
    backgroundRegistry.add(breedingTemplate);
    additionalDbRules.addBreedingRules(breedingTemplate);
    immaculateDbRules.addBreedingRules(breedingTemplate);
    outcasteDbRules.addBreedingRules(breedingTemplate);
    nativeLookshyDbRules.addBreedingRules(breedingTemplate);
    realmLookshyDbRules.addBreedingRules(breedingTemplate);
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_COMMAND, CharacterType.DB));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, CharacterType.DB));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_FAMILY, new ITemplateType[] {
        dynastTemplateType,
        LookshyDbTemplate.TEMPLATE_TYPE,
        LookshyRealmDbTemplate.TEMPLATE_TYPE }));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_HENCHMEN, CharacterType.DB));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_REPUTATION, CharacterType.DB));
    addLookshyBackgrounds(backgroundRegistry);
    addCultBackgrounds(backgroundRegistry);
    addSorcery(backgroundRegistry);
  }

  private void addCultBackgrounds(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    ITemplateType[] cultTemplates = new ITemplateType[] {
        KetherRockDbTemplate.TEMPLATE_TYPE,
        SequesteredTabernacleDbTemplate.TEMPLATE_TYPE };
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ILLUMINATION, cultTemplates));
  }

  private void addSorcery(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    TemplateTypeBackgroundTemplate sorceryBackground = new TemplateTypeBackgroundTemplate(
        BACKGROUND_ID_SORCERY,
        new ITemplateType[] {
            dynastTemplateType,
            LookshyDbTemplate.TEMPLATE_TYPE,
            LookshyOutcasteDbTemplate.TEMPLATE_TYPE,
            LookshyRealmDbTemplate.TEMPLATE_TYPE,
            KetherRockDbTemplate.TEMPLATE_TYPE,
            SequesteredTabernacleDbTemplate.TEMPLATE_TYPE },
        LowerableState.Immutable);
    backgroundRegistry.add(sorceryBackground);
    nativeLookshyDbRules.addSorceryRules(sorceryBackground);
    realmLookshyDbRules.addSorceryRules(sorceryBackground);
    additionalDbRules.addSorceryRules(sorceryBackground);
    cultRules.addSorceryRules(sorceryBackground);
    sequesteredTabernacleRules.addSorceryRules(sorceryBackground);
  }

  private void addLookshyBackgrounds(IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    ITemplateType[] lookshyTemplateTypes = new ITemplateType[] {
        LookshyDbTemplate.TEMPLATE_TYPE,
        LookshyOutcasteDbTemplate.TEMPLATE_TYPE,
        LookshyRealmDbTemplate.TEMPLATE_TYPE };
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ARSENAL, lookshyTemplateTypes));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_RETAINERS, lookshyTemplateTypes));
  }

  private void registerDbTemplate(ITemplateRegistry templateRegistry, CharmCache charmProvider) {
    templateRegistry.register(new PatricianOutcasteDBTemplate(charmProvider, outcasteDbRules));
    templateRegistry.register(new LowerClassOutcasteDbTemplate(charmProvider, outcasteDbRules));
    templateRegistry.register(new ThresholdOutcasteDbTemplate(charmProvider, new DefaultAdditionalRules(
        BACKGROUND_ID_BREEDING,
        BACKGROUND_ID_COMMAND,
        BACKGROUND_ID_CONNECTIONS,
        BACKGROUND_ID_HENCHMEN,
        BACKGROUND_ID_REPUTATION)));
    templateRegistry.register(new LookshyDbTemplate(charmProvider, nativeLookshyDbRules));
    templateRegistry.register(new LookshyOutcasteDbTemplate(charmProvider, nativeLookshyDbRules));
    templateRegistry.register(new LookshyRealmDbTemplate(charmProvider, realmLookshyDbRules));
    templateRegistry.register(new PirateOutcasteDbTemplate(charmProvider, outcasteDbRules));
    templateRegistry.register(new PirateRealmDbTemplate(charmProvider, additionalDbRules));
    templateRegistry.register(new KetherRockDbTemplate(charmProvider, cultRules));
    templateRegistry.register(new SequesteredTabernacleDbTemplate(charmProvider, sequesteredTabernacleRules));
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