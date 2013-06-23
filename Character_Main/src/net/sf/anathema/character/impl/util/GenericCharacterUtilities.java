package net.sf.anathema.character.impl.util;

import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.hero.model.Hero;

public class GenericCharacterUtilities {

  public static GenericCharacter createGenericCharacter(Hero character) {
    return new GenericCharacter(character);
  }
}