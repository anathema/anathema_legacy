package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.DataFileProvider;

public class HeroEnvironmentInitializer {

  private final ResourceLoader resourceLoader;
  private final ObjectFactory objectFactory;

  public HeroEnvironmentInitializer(ResourceLoader resourceLoader, ObjectFactory objectFactory) {
    this.resourceLoader = resourceLoader;
    this.objectFactory = objectFactory;
  }

  public HeroEnvironment initEnvironmnent(DataFileProvider dataFileProvider) throws InitializationException {
    HeroEnvironment environment = createEnvironment(dataFileProvider);
    addCharacterTemplates(environment);
    return environment;
  }

  private CharacterGenerics createEnvironment(DataFileProvider dataFileProvider) {
    IExtensibleDataSetProvider dataSetProvider = loadExtensibleResources();
    return new CharacterGenerics(dataFileProvider, objectFactory, dataSetProvider);
  }

  private IExtensibleDataSetProvider loadExtensibleResources() {
    DataSetInitializer dataSetInitializer = new DataSetInitializer(resourceLoader, objectFactory);
    return dataSetInitializer.initializeExtensibleResources();
  }

  private void addCharacterTemplates(HeroEnvironment environment) {
    for (ICharacterType type : environment.getCharacterTypes().findAll()) {
      new CharacterTemplateInitializer(type).addCharacterTemplates(environment);
    }
  }
}