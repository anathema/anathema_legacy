package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.framework.module.ICharacterModule;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IDataFileProvider;

import java.util.Collection;

public class CharacterModuleContainerInitializer {

  private final ResourceLoader resourceLoader;
  private final Instantiater instantiater;

  public CharacterModuleContainerInitializer(ResourceLoader resourceLoader, Instantiater instantiater) {
    this.resourceLoader = resourceLoader;
    this.instantiater = instantiater;
  }

  public CharacterModuleContainer initContainer(IDataFileProvider dataFileProvider) throws InitializationException {
    CharacterModuleContainer container = createModuleContainer(dataFileProvider);
    addModules(container);
    return container;
  }

  private CharacterModuleContainer createModuleContainer(IDataFileProvider dataFileProvider) {
    IExtensibleDataSetProvider dataSetManager = loadExtensibleResources();
    return new CharacterModuleContainer(dataSetManager, dataFileProvider, instantiater);
  }

  private void addModules(CharacterModuleContainer container) {
    Collection<ICharacterModule> modules = instantiater.instantiateOrdered(CharacterModule.class);
    for (ICharacterModule module : modules) {
      container.addCharacterGenericsModule(module);
    }
  }

  private IExtensibleDataSetProvider loadExtensibleResources() {
    DataSetInitializer dataSetInitializer = new DataSetInitializer(resourceLoader, instantiater);
    return dataSetInitializer.initializeExtensibleResources();
  }
}