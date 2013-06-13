package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.NullAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.backgrounds.BackgroundRegistry;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.CharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.impl.template.magic.CharmProvider;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ReflectionCharacterTypes;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.IdentificateRegistry;
import net.sf.anathema.lib.registry.Registry;

public class CharacterGenerics implements ICharacterGenerics {

  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = new BackgroundRegistry();
  private final ITemplateRegistry templateRegistry = new TemplateRegistry();
  private final IRegistry<String, IAdditionalModelFactory> additionalModelRegistry = new Registry<>();
  private final IRegistry<String, IAdditionalViewFactory> additionalViewRegistry = new Registry<>();
  private final IRegistry<String, IAdditionalPersisterFactory> additionalPersisterRegistry;
  private final IIdentificateRegistry<IGlobalAdditionalTemplate> additionalTemplateRegistry = new IdentificateRegistry<>();
  private final ICharacterTemplateRegistryCollection templateRegistries;
  private final IRegistry<ICharacterType, ICasteCollection> casteCollectionRegistry = new Registry<>();
  private final IRegistry<String, IAdditionalTemplateParser> additionalTemplateParserRegistry = new Registry<>();
  private final ICharmProvider charmProvider;
  private final IDataFileProvider dataFileProvider;
  private final IExtensibleDataSetProvider dataSetProvider;
  private final Instantiater instantiater;
  private final CharacterTypes characterTypes;

  public CharacterGenerics(IDataFileProvider dataFileProvider, Instantiater instantiater, IExtensibleDataSetProvider dataSetProvider) {
    this.instantiater = instantiater;
    this.additionalPersisterRegistry = new Registry<String, IAdditionalPersisterFactory>(new NullAdditionalPersisterFactory());
    this.dataFileProvider = dataFileProvider;
    this.dataSetProvider = dataSetProvider;
    this.charmProvider = new CharmProvider(dataSetProvider.getDataSet(ICharmCache.class));
    this.templateRegistries = new CharacterTemplateRegistryCollection(dataSetProvider.getDataSet(ICharacterTemplateExtensionResourceCache.class));
    this.characterTypes = new ReflectionCharacterTypes(instantiater);
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
  public CharacterTypes getCharacterTypes() {
    return characterTypes;
  }

  @Override
  public ICharmProvider getCharmProvider() {
    return charmProvider;
  }

  @Override
  public IDataFileProvider getDataFileProvider() {
    return dataFileProvider;
  }

  @Override
  public <T extends IExtensibleDataSet> T getDataSet(Class<T> set) {
    return dataSetProvider.getDataSet(set);
  }
}
