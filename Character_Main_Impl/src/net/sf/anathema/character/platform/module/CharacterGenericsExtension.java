package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.DataFileProvider;

@Extension(id = "net.sf.anathema.character.generic.framework.ICharacterGenericsExtension")
public class CharacterGenericsExtension implements ICharacterGenericsExtension, IAnathemaExtension {

  private ICharacterGenerics characterGenerics;

  @Override
  public void initialize(DataFileProvider dataFileProvider, AnnotationFinder finder, ResourceLoader loader) throws
          InitializationException {
    ReflectionObjectFactory instantiater = new ReflectionObjectFactory(finder);
    CharacterModuleContainerInitializer initializer = new CharacterModuleContainerInitializer(loader, instantiater);
    CharacterModuleContainer container = initializer.initContainer(dataFileProvider);
    this.characterGenerics = container.getCharacterGenerics();
  }

  @Override
  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }
}