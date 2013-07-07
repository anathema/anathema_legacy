package net.sf.anathema.character.main.type;

public interface CharacterTypes {
  ICharacterType findById(String id);

  ICharacterType[] findAll();
}
