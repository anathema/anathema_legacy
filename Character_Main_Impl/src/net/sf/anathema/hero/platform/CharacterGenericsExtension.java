package net.sf.anathema.hero.platform;

import net.sf.anathema.character.main.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.main.framework.module.CharacterModuleContainer;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.DataFileProvider;

@Extension(id = "CharacterGenericsExtension")
public class CharacterGenericsExtension implements ICharacterGenericsExtension, IAnathemaExtension {

  private HeroEnvironment characterGenerics;

  @Override
  public void initialize(DataFileProvider dataFileProvider, ObjectFactory instantiater, ResourceLoader loader) throws
          InitializationException {
    CharacterModuleContainerInitializer initializer = new CharacterModuleContainerInitializer(loader, instantiater);
    CharacterModuleContainer container = initializer.initContainer(dataFileProvider);
    this.characterGenerics = container.getCharacterGenerics();
  }

  @Override
  public HeroEnvironment getCharacterGenerics() {
    return characterGenerics;
  }
}