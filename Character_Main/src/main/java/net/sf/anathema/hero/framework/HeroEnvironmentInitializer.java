package net.sf.anathema.hero.framework;

import net.sf.anathema.character.framework.CharacterTemplateInitializer;
import net.sf.anathema.character.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.repository.DataFileProvider;

public class HeroEnvironmentInitializer {

  private final ResourceLoader resourceLoader;
  private final ObjectFactory objectFactory;

  public HeroEnvironmentInitializer(ResourceLoader resourceLoader, ObjectFactory objectFactory) {
    this.resourceLoader = resourceLoader;
    this.objectFactory = objectFactory;
  }

  public HeroEnvironment initEnvironment(DataFileProvider dataFileProvider) throws InitializationException {
    HeroEnvironment environment = createEnvironment(dataFileProvider);
    new CharacterTemplateInitializer(environment).addCharacterTemplates();
    return environment;
  }

  private HeroEnvironmentImpl createEnvironment(DataFileProvider dataFileProvider) {
    DataSetInitializer dataSetInitializer = new DataSetInitializer(resourceLoader, objectFactory);
    IExtensibleDataSetProvider dataSetProvider = dataSetInitializer.initializeExtensibleResources();
    return new HeroEnvironmentImpl(dataFileProvider, objectFactory, dataSetProvider);
  }

}