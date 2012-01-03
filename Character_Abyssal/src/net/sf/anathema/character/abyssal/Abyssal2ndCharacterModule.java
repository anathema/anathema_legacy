package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.abyssal.equipment.FangTemplate;
import net.sf.anathema.character.abyssal.generic.ApocalypticEvolution;
import net.sf.anathema.character.abyssal.generic.EssenceFlow;
import net.sf.anathema.character.abyssal.generic.InfiniteMastery;
import net.sf.anathema.character.abyssal.generic.RaveningMouth;
import net.sf.anathema.character.abyssal.generic.SupremePerfection;
import net.sf.anathema.character.abyssal.reporting.Extended2ndEditionAbyssalPartEncoder;
import net.sf.anathema.character.abyssal.reporting.content.Abyssal2ndResonanceContent;
import net.sf.anathema.character.abyssal.reporting.content.Abyssal2ndResonanceContentFactory;
import net.sf.anathema.character.abyssal.reporting.rendering.Anima2ndEditionEncoderFactory;
import net.sf.anathema.character.abyssal.reporting.rendering.Resonance2ndEditionEncoderFactory;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceModelFactory;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceParser;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonancePersisterFactory;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceTemplate;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceViewFactory;
import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
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
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.ABYSSAL;

public class Abyssal2ndCharacterModule extends NullObjectCharacterModuleAdapter {
  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;

  @SuppressWarnings("unused")
  private static final TemplateType abyssalTemplateType = new TemplateType(ABYSSAL);

  private static final TemplateType loyalAbyssalTemplateType = new TemplateType(ABYSSAL, new Identificate("default")); //$NON-NLS-1$
  private static final TemplateType revisedLoyalAbyssalTemplateType = new TemplateType(ABYSSAL, new Identificate("RevisedLoyalAbyssal"));
  //$NON-NLS-1$

  @SuppressWarnings("unused")
  private static final TemplateType renegadeAbyssalTemplateType = new TemplateType(ABYSSAL, new Identificate("RenegadeAbyssal")); //$NON-NLS-1$
  @SuppressWarnings("unused")
  private static final TemplateType revisedRenegadeAbyssalTemplateType = new TemplateType(ABYSSAL, new Identificate("RevisedRenegadeAbyssal"));
  //$NON-NLS-1$

  public static final String BACKGROUND_ID_ABYSSAL_COMMAND = "AbyssalCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_LIEGE = "Liege"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SPIES = "Spies"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_UNDERWORLD_MANSE = "UnderworldManse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WHISPERS = "Whispers"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    IRegistry<ICharacterType, IMagicStats[]> genericCharmRegistry = characterGenerics.getGenericCharmStatsRegistry();
    genericCharmRegistry.register(ABYSSAL, new IMagicStats[]{new FirstExcellency(ABYSSAL, ExaltedSourceBook.SecondEdition, "1 m per die"),
            //$NON-NLS-1$
            new SecondExcellency(ABYSSAL, ExaltedSourceBook.SecondEdition), new ThirdExcellency(ABYSSAL, "4 m", ExaltedSourceBook.SecondEdition), new InfiniteMastery(), new RaveningMouth(), new EssenceFlow(), new ApocalypticEvolution(), new SupremePerfection()});

    characterGenerics.getAdditionalTemplateParserRegistry().register(AbyssalResonanceTemplate.ID, new AbyssalResonanceParser());

    characterGenerics.getCasteCollectionRegistry().register(ABYSSAL, new CasteCollection(AbyssalCaste.values()));

    IEquipmentAdditionalModelTemplate equipmentTemplate = (IEquipmentAdditionalModelTemplate) characterGenerics.getGlobalAdditionalTemplateRegistry().getById(IEquipmentAdditionalModelTemplate.ID);
    equipmentTemplate.addNaturalWeaponTemplate(ABYSSAL, new FangTemplate());
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/LoyalAbyssal2nd.template", "moep_Abyssals_"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/LoyalAbyssal2ndRevised.template", "moep_Abyssals_"); //$NON-NLS-1$

    registerParsedTemplate(characterGenerics, "template/RenegadeAbyssal2nd.template", "moep_Abyssals_"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/RenegadeAbyssal2ndRevised.template", "moep_Abyssals_"); //$NON-NLS-1$
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_ABYSSAL_COMMAND, ABYSSAL));

    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_LIEGE, loyalAbyssalTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_LIEGE, revisedLoyalAbyssalTemplateType));

    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SPIES, ABYSSAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_UNDERWORLD_MANSE, ABYSSAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_WHISPERS, ABYSSAL));
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    moduleObject.getContentRegistry().addFactory(Abyssal2ndResonanceContent.class, new Abyssal2ndResonanceContentFactory(resources));
    ExtendedEncodingRegistry extendedRegistry = moduleObject.getExtendedEncodingRegistry();
    extendedRegistry.setPartEncoder(ABYSSAL, SecondEdition, new Extended2ndEditionAbyssalPartEncoder(resources, extendedRegistry, ESSENCE_MAX));
    moduleObject.getEncoderRegistry().add(new Anima2ndEditionEncoderFactory());
    moduleObject.getEncoderRegistry().add(new Resonance2ndEditionEncoderFactory());
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = AbyssalResonanceTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new AbyssalResonanceModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new AbyssalResonanceViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new AbyssalResonancePersisterFactory());
  }
}
