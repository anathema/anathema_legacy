package net.sf.anathema.character.main.type;

public interface CharacterTypes extends Iterable<CharacterType> {
  CharacterType findById(String id);
}
