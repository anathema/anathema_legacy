package net.sf.anathema.character.infernal;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterTypeModule;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.InfernalCharacterType;
import net.sf.anathema.character.infernal.caste.InfernalCaste;
import net.sf.anathema.character.infernal.patron.InfernalPatronModelFactory;
import net.sf.anathema.character.infernal.patron.InfernalPatronParser;
import net.sf.anathema.character.infernal.patron.InfernalPatronTemplate;
import net.sf.anathema.character.infernal.patron.InfernalPatronViewFactory;
import net.sf.anathema.character.infernal.patron.persistence.InfernalPatronPersisterFactory;
import net.sf.anathema.character.infernal.urge.*;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;

@CharacterModule
public class InfernalCharacterModule extends CharacterTypeModule {
  public static final ICharacterType type = new InfernalCharacterType();
  public static final String BACKGROUND_ID_UNWOVEN_COADJUTOR = "UnwovenCoadjutor"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_DEMONIC_FAMILIAR = "DemonicFamiliar"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_PAST_LIFE = "PastLife"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SAVANT = "InfernalSavant"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SPIES = "Spies"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCasteCollectionRegistry().register(type, new CasteCollection(InfernalCaste.values()));
    characterGenerics.getAdditionalTemplateParserRegistry().register(InfernalPatronTemplate.ID, new InfernalPatronParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(InfernalUrgeTemplate.ID, new InfernalUrgeParser());
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerInfernalPatron(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerInfernalUrge(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }

  private void registerInfernalPatron(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                                      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = InfernalPatronTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new InfernalPatronModelFactory());
    additionalViewFactoryRegistry.register(templateId, new InfernalPatronViewFactory());
    persisterFactory.register(templateId, new InfernalPatronPersisterFactory());
  }

  private void registerInfernalUrge(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                                    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry, IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = InfernalUrgeTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new InfernalUrgeModelFactory());
    additionalViewFactoryRegistry.register(templateId, new InfernalUrgeViewFactory());
    persisterFactory.register(templateId, new InfernalUrgePersisterFactory());
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_UNWOVEN_COADJUTOR, type));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_DEMONIC_FAMILIAR, type));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SAVANT, type));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_PAST_LIFE, type));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SPIES, type));
  }

  @Override
  protected ICharacterType getType() {
    return type;
  }
}