package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.abyssal.additional.AdditionalAbyssalRules;
import net.sf.anathema.character.abyssal.additional.AdditionalLoyalAbyssalRules;
import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.abyssal.caste.IAbyssalSpecialCharms;
import net.sf.anathema.character.abyssal.equipment.FangTemplate;
import net.sf.anathema.character.abyssal.reporting.FirstEditionAbyssalPartEncoder;
import net.sf.anathema.character.abyssal.template.LoyalAbyssalTemplate;
import net.sf.anathema.character.abyssal.template.RenegadeAbyssalTemplate;
import net.sf.anathema.character.abyssal.template.UnsupportedAbyssal2ndTemplate;
import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.resources.IResources;

public class AbyssalCharacterModule extends NullObjectCharacterModuleAdapter {
  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;
  public static final String BACKGROUND_ID_ABYSSAL_COMMAND = "AbyssalCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_LIEGE = "Liege"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_NECROMANCY = "Necromancy"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SPIES = "Spies"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_UNDERWORLD_MANSE = "UnderworldManse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WHISPERS = "Whispers"; //$NON-NLS-1$
  private final AdditionalLoyalAbyssalRules additionalLoyalAbyssalRules = new AdditionalLoyalAbyssalRules();
  private final AdditionalAbyssalRules additionalRenegadeAbyssalRules = new AdditionalAbyssalRules();

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    ISpecialCharm[] specialCharms = new ISpecialCharm[] {
        IAbyssalSpecialCharms.OX_BODY_TECHNIQUE,
        IAbyssalSpecialCharms.INSENSIBLE_CORPSE_TECHNIQUE,
        IAbyssalSpecialCharms.ESSENCE_ENGORGEMENT_TECHNIQUE };
    characterGenerics.getCharmProvider().setSpecialCharms(
        CharacterType.ABYSSAL,
        ExaltedEdition.FirstEdition,
        specialCharms);
    characterGenerics.getCasteCollectionRegistry().register(
        CharacterType.ABYSSAL,
        new CasteCollection(AbyssalCaste.values()));
    IEquipmentAdditionalModelTemplate equipmentTemplate = (IEquipmentAdditionalModelTemplate) characterGenerics.getGlobalAdditionalTemplateRegistry()
        .getById(IEquipmentAdditionalModelTemplate.ID);
    equipmentTemplate.addNaturalWeaponTemplate(CharacterType.ABYSSAL, new FangTemplate());
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    ICharmCache charmProvider = CharmCache.getInstance();
    ITemplateRegistry templateRegistry = characterGenerics.getTemplateRegistry();
    initTemplate(
        templateRegistry, new LoyalAbyssalTemplate(
        charmProvider,
        additionalLoyalAbyssalRules), additionalLoyalAbyssalRules);
    initTemplate(templateRegistry, new RenegadeAbyssalTemplate(
        charmProvider,
        additionalRenegadeAbyssalRules), additionalRenegadeAbyssalRules);
    templateRegistry.register(new UnsupportedAbyssal2ndTemplate(charmProvider));
  }

  private void initTemplate(
      ITemplateRegistry templateRegistry,
      ICharacterTemplate template,
      AdditionalAbyssalRules additionalRules) {
    templateRegistry.register(template);    
    additionalRules.addEssenceEngorgementTechniqueRules(IAbyssalSpecialCharms.ESSENCE_ENGORGEMENT_TECHNIQUE);
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_ABYSSAL_COMMAND, CharacterType.ABYSSAL));
    IBackgroundTemplate backgroundTemplate = new TemplateTypeBackgroundTemplate(
        BACKGROUND_ID_LIEGE,
        LoyalAbyssalTemplate.TEMPLATE_TYPE);
    backgroundRegistry.add(backgroundTemplate);
    additionalLoyalAbyssalRules.addLiegeRules(backgroundTemplate);
    IBackgroundTemplate necromancyBackground = new CharacterTypeBackgroundTemplate(
        BACKGROUND_ID_NECROMANCY,
        CharacterType.ABYSSAL,
        LowerableState.Immutable);
    backgroundRegistry.add(necromancyBackground);
    additionalLoyalAbyssalRules.addNecromancyRules(necromancyBackground);
    additionalRenegadeAbyssalRules.addNecromancyRules(necromancyBackground);
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SPIES, CharacterType.ABYSSAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_UNDERWORLD_MANSE, CharacterType.ABYSSAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_WHISPERS, CharacterType.ABYSSAL));
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    IPdfPartEncoder firstEditionEncoder = new FirstEditionAbyssalPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.ABYSSAL, ExaltedEdition.FirstEdition, firstEditionEncoder);
  }
}