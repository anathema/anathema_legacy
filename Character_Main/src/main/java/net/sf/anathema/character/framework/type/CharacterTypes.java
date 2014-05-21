package net.sf.anathema.character.framework.type;

public interface CharacterTypes extends Iterable<CharacterType> {
  CharacterType findById(String id);
}
