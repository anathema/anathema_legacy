package net.sf.anathema.character.infernal;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.infernal.caste.InfernalCaste;
import net.sf.anathema.character.infernal.patron.InfernalPatronModelFactory;
import net.sf.anathema.character.infernal.patron.InfernalPatronParser;
import net.sf.anathema.character.infernal.patron.InfernalPatronTemplate;
import net.sf.anathema.character.infernal.patron.InfernalPatronViewFactory;
import net.sf.anathema.character.infernal.patron.persistence.InfernalPatronPersisterFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeModelFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeParser;
import net.sf.anathema.character.infernal.urge.InfernalUrgePersisterFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.infernal.urge.InfernalUrgeViewFactory;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;

import static net.sf.anathema.character.generic.type.CharacterType.INFERNAL;

@CharacterModule
public class InfernalCharacterModule extends NullObjectCharacterModuleAdapter
{
  public static final String BACKGROUND_ID_UNWOVEN_COADJUTOR = "UnwovenCoadjutor"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_DEMONIC_FAMILIAR = "DemonicFamiliar"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_PAST_LIFE = "PastLife"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SAVANT = "InfernalSavant"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SPIES = "Spies"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCasteCollectionRegistry().register(INFERNAL, new CasteCollection(InfernalCaste.values()));
    characterGenerics.getAdditionalTemplateParserRegistry().register(InfernalPatronTemplate.ID, new InfernalPatronParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(InfernalUrgeTemplate.ID, new InfernalUrgeParser());
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Infernal2nd.template"); //$NON-NLS-1$
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
  public void addBackgroundTemplates(ICharacterGenerics generics)
  {
	  IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
	  backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_UNWOVEN_COADJUTOR, INFERNAL));
	  backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_DEMONIC_FAMILIAR, INFERNAL));
	  backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SAVANT, INFERNAL));
	  backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_PAST_LIFE, INFERNAL));
	  backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SPIES, INFERNAL));
  }
}