package net.sf.anathema.character.generic.type;

public class CharacterTypes {

  public static ICharacterType findById(String id) {
    return CharacterType.getById(id);
  }

  public static ICharacterType[] findAll() {
    return CharacterType.values();
  }
}