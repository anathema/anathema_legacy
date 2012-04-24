package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

@Extension(id="net.sf.anathema.character.generic.framework.ICharacterGenericsExtension")
public class CharacterGenericsExtension implements ICharacterGenericsExtension, IAnathemaExtension {

  private ICharacterGenerics characterGenerics;

  @Override
  public void initialize(IResources resources,
		  IDataFileProvider dataFileProvider,
		  AnathemaReflections reflections) throws InitializationException {
    CharacterModuleContainer container = new CharacterModuleContainerInitializer(reflections).initContainer(
        resources,
        dataFileProvider);
    this.characterGenerics = container.getCharacterGenerics();
  }

  @Override
  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }
}