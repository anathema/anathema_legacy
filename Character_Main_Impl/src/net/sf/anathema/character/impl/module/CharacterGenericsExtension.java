package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.framework.extension.IExtensionPoint;

public class CharacterGenericsExtension implements ICharacterGenericsExtension, IExtensionPoint {

  private final ICharacterGenerics characterGenerics;

  public CharacterGenericsExtension(ICharacterGenerics characterGenerics) {
    this.characterGenerics = characterGenerics;
  }

  public ICharacterGenerics getCharacterGenerics() {
    return characterGenerics;
  }
}