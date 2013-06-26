package net.sf.anathema.character.generic;

import net.sf.anathema.hero.model.Hero;

public class GenericCharacterUtilities {

  public static GenericCharacter createGenericCharacter(Hero character) {
    return new GenericCharacter(character);
  }
}