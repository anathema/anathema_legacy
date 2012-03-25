package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceModelFactory;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceParser;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonancePersisterFactory;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceTemplate;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceViewFactory;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.generic.type.CharacterType.ABYSSAL;

@CharacterModule
public class Abyssal2ndCharacterModule extends NullObjectCharacterModuleAdapter {

  @SuppressWarnings("unused")
  private static final TemplateType abyssalTemplateType = new TemplateType(ABYSSAL);

  private static final TemplateType loyalAbyssalTemplateType = new TemplateType(ABYSSAL,
          new Identificate("default")); //$NON-NLS-1$

  @SuppressWarnings("unused")
  private static final TemplateType renegadeAbyssalTemplateType = new TemplateType(ABYSSAL,
          new Identificate("RenegadeAbyssal")); //$NON-NLS-1$
  @SuppressWarnings("unused")

  public static final String BACKGROUND_ID_ABYSSAL_COMMAND = "AbyssalCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_LIEGE = "Liege"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SPIES = "Spies"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_UNDERWORLD_MANSE = "UnderworldManse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WHISPERS = "Whispers"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalTemplateParserRegistry().register(AbyssalResonanceTemplate.ID,
            new AbyssalResonanceParser());

    characterGenerics.getCasteCollectionRegistry().register(ABYSSAL, new CasteCollection(AbyssalCaste.values()));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/LoyalAbyssal2nd.template", "moep_Abyssals_"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/RenegadeAbyssal2nd.template", "moep_Abyssals_"); //$NON-NLS-1$
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_ABYSSAL_COMMAND, ABYSSAL));

    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_LIEGE, loyalAbyssalTemplateType));

    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SPIES, ABYSSAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_UNDERWORLD_MANSE, ABYSSAL));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_WHISPERS, ABYSSAL));
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
