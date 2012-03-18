package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.NullAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.backgrounds.BackgroundRegistry;
import net.sf.anathema.character.generic.framework.module.object.CharacterModuleObjectMap;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.CharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.impl.template.magic.CharmProvider;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.magic.description.AggregatedCharmDescription;
import net.sf.anathema.character.generic.magic.description.AggregatedCharmDescriptionProvider;
import net.sf.anathema.character.generic.magic.description.CharmDescriptionProvider;
import net.sf.anathema.character.generic.magic.description.CharmDescriptionProviderFactory;
import net.sf.anathema.character.generic.magic.description.RegisteredCharmDescriptionProviderFactory;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.IdentificateRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;

public class CharacterGenerics implements ICharacterGenerics {

  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = new BackgroundRegistry();
  private final ITemplateRegistry templateRegistry = new TemplateRegistry();
  private final IRegistry<String, IAdditionalModelFactory> additionalModelRegistry = new Registry<String, IAdditionalModelFactory>();
  private final IRegistry<String, IAdditionalViewFactory> additionalViewRegistry = new Registry<String, IAdditionalViewFactory>();
  private final IRegistry<String, IAdditionalPersisterFactory> additionalPersisterRegistry;
  private final IIdentificateRegistry<IGlobalAdditionalTemplate> additionalTemplateRegistry = new IdentificateRegistry<IGlobalAdditionalTemplate>();
  private final ICharacterTemplateRegistryCollection templateRegistries = new CharacterTemplateRegistryCollection();
  private final IRegistry<ICharacterType, ICasteCollection> casteCollectionRegistry = new Registry<ICharacterType, ICasteCollection>();
  private final IRegistry<String, IAdditionalTemplateParser> additionalTemplateParserRegistry = new Registry<String, IAdditionalTemplateParser>();
  private final ICharmProvider charmProvider = new CharmProvider(CharmCache.getInstance());
  private final CharacterModuleObjectMap moduleObjectMap = new CharacterModuleObjectMap();
  private final IDataFileProvider dataFileProvider;
  private final Instantiater instantiater;

  public CharacterGenerics(IDataFileProvider dataFileProvider, Instantiater instantiater) {
    this.instantiater = instantiater;
    this.additionalPersisterRegistry = new Registry<String, IAdditionalPersisterFactory>(
            new NullAdditionalPersisterFactory());
    this.dataFileProvider = dataFileProvider;
  }

  @Override
  public IIdentificateRegistry<IBackgroundTemplate> getBackgroundRegistry() {
    return backgroundRegistry;
  }

  @Override
  public ITemplateRegistry getTemplateRegistry() {
    return templateRegistry;
  }

  @Override
  public IRegistry<String, IAdditionalModelFactory> getAdditionalModelFactoryRegistry() {
    return additionalModelRegistry;
  }

  @Override
  public IRegistry<String, IAdditionalViewFactory> getAdditionalViewFactoryRegistry() {
    return additionalViewRegistry;
  }

  @Override
  public IRegistry<String, IAdditionalPersisterFactory> getAdditonalPersisterFactoryRegistry() {
    return additionalPersisterRegistry;
  }

  @Override
  public IIdentificateRegistry<IGlobalAdditionalTemplate> getGlobalAdditionalTemplateRegistry() {
    return additionalTemplateRegistry;
  }

  @Override
  public ICharacterTemplateRegistryCollection getCharacterTemplateRegistries() {
    return templateRegistries;
  }

  @Override
  public IRegistry<ICharacterType, ICasteCollection> getCasteCollectionRegistry() {
    return casteCollectionRegistry;
  }

  @Override
  public IRegistry<String, IAdditionalTemplateParser> getAdditionalTemplateParserRegistry() {
    return additionalTemplateParserRegistry;
  }

  @Override
  public Instantiater getInstantiater() {
    return instantiater;
  }

  @Override
  public ICharmProvider getCharmProvider() {
    return charmProvider;
  }

  @Override
  public CharacterModuleObjectMap getModuleObjectMap() {
    return moduleObjectMap;
  }

  @Override
  public IDataFileProvider getDataFileProvider() {
    return dataFileProvider;
  }
}
