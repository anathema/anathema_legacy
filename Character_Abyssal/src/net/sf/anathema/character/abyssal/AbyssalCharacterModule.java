package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.abyssal.additional.AdditionalAbyssalRules;
import net.sf.anathema.character.abyssal.additional.AdditionalLoyalAbyssalRules;
import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.abyssal.caste.IAbyssalSpecialCharms;
import net.sf.anathema.character.abyssal.reporting.AbyssalVoidstateReportTemplate;
import net.sf.anathema.character.abyssal.template.LoyalAbyssalTemplate;
import net.sf.anathema.character.abyssal.template.RenegadeAbyssalTemplate;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.resources.IResources;

public class AbyssalCharacterModule extends NullObjectCharacterModuleAdapter {

  private static final Logger logger = Logger.getLogger(AbyssalCharacterModule.class);
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
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    try {
      ICharmCache charmProvider = CharmCache.getInstance();
      initTemplate(characterGenerics.getTemplateRegistry(), new LoyalAbyssalTemplate(
          charmProvider,
          additionalLoyalAbyssalRules), additionalLoyalAbyssalRules);
      initTemplate(characterGenerics.getTemplateRegistry(), new RenegadeAbyssalTemplate(
          charmProvider,
          additionalRenegadeAbyssalRules), additionalRenegadeAbyssalRules);
    }
    catch (PersistenceException exception) {
      logger.error("Abyssal Charms not found", exception); //$NON-NLS-1$
    }
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
    ITemplateType[] loyalAbyssalTemplateType = new ITemplateType[] { LoyalAbyssalTemplate.TEMPLATE_TYPE };
    ITemplateType[] allAbyssalTemplateType = new ITemplateType[] {
        LoyalAbyssalTemplate.TEMPLATE_TYPE,
        RenegadeAbyssalTemplate.TEMPLATE_TYPE };
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ABYSSAL_COMMAND, allAbyssalTemplateType));
    IBackgroundTemplate backgroundTemplate = new TemplateTypeBackgroundTemplate(
        BACKGROUND_ID_LIEGE,
        loyalAbyssalTemplateType);
    backgroundRegistry.add(backgroundTemplate);
    additionalLoyalAbyssalRules.addLiegeRules(backgroundTemplate);
    TemplateTypeBackgroundTemplate necromancyBackground = new TemplateTypeBackgroundTemplate(
        BACKGROUND_ID_NECROMANCY,
        allAbyssalTemplateType,
        LowerableState.Immutable);
    backgroundRegistry.add(necromancyBackground);
    additionalLoyalAbyssalRules.addNecromancyRules(necromancyBackground);
    additionalRenegadeAbyssalRules.addNecromancyRules(necromancyBackground);
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SPIES, allAbyssalTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_UNDERWORLD_MANSE, allAbyssalTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_WHISPERS, allAbyssalTemplateType));
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    generics.getReportTemplateRegistry().add(new AbyssalVoidstateReportTemplate(resources));
  }
}