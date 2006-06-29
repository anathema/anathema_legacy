package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.lib.resources.IResources;

public class CharacterGenericsExtension implements ICharacterGenericsExtension, IAnathemaExtension {

  private ICharacterGenerics characterGenerics;

  public void initialize(IResources resources) {
    CharacterModuleContainer container = new CharacterModuleContainerInitializer().initContainer(resources);
    this.characterGenerics = container.getCharacterGenerics();
  }

  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }
}