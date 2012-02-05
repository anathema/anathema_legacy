package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.framework.module.ICharacterModule;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;

public class CharacterModuleContainerInitializer {

  private final Instantiater instantiater;

  public CharacterModuleContainerInitializer(Instantiater instantiater) {
    this.instantiater = instantiater;
  }

  public CharacterModuleContainer initContainer(IResources resources, IDataFileProvider dataFileProvider) throws InitializationException {
    CharacterModuleContainer container = new CharacterModuleContainer(resources, dataFileProvider, instantiater);
    Collection<ICharacterModule<ICharacterModuleObject>> modules = instantiater.instantiateAll(CharacterModule.class);
    for (ICharacterModule<ICharacterModuleObject> module : modules) {
      container.addCharacterGenericsModule(module);
    }
    return container;
  }
}