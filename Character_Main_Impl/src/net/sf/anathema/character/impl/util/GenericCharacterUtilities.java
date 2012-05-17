package net.sf.anathema.character.impl.util;

import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.model.ICharacter;

public class GenericCharacterUtilities {

  public static GenericCharacter createGenericCharacter(ICharacter character) {
    return new GenericCharacter(character);
  }
}