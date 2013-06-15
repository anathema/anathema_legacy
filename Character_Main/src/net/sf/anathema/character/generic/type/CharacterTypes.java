package net.sf.anathema.character.generic.type;

public interface CharacterTypes {
  ICharacterType findById(String id);

  ICharacterType[] findAll();
}
