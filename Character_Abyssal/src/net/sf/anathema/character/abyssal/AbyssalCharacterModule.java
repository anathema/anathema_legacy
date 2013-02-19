package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.abyssal.resonance.*;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterTypeModule;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;

@CharacterModule
public class AbyssalCharacterModule extends CharacterTypeModule {

  public static final ICharacterType type = new AbyssalCharacterType();
  public static final String BACKGROUND_ID_ABYSSAL_COMMAND = "AbyssalCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_LIEGE = "Liege"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SPIES = "AbyssalSpies"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_UNDERWORLD_MANSE = "UnderworldManse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WHISPERS = "Whispers"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalTemplateParserRegistry().register(AbyssalResonanceTemplate.ID,
            new AbyssalResonanceParser());
    characterGenerics.getCasteCollectionRegistry().register(type, new CasteCollection(AbyssalCaste.values()));
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ABYSSAL_COMMAND, getDefaultAndCustomTemplates(generics)));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_LIEGE, getDefaultAndCustomTemplates(generics)));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SPIES, getDefaultAndCustomTemplates(generics)));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_UNDERWORLD_MANSE, getDefaultAndCustomTemplates(generics)));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_WHISPERS, type));
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

  @Override
  protected ICharacterType getType() {
    return type;
  }
}
