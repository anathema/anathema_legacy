package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;

public class DummyCharacterTypes implements CharacterTypes {
  private CharacterType type;

  @Override
  public CharacterType findById(String id) {
    if (type.getId().equals(id)) {
      return type;
    }
    throw new IllegalArgumentException("No type defined for id:" + id);
  }

  @Override
  public CharacterType[] findAll() {
    return new CharacterType[]{type};
  }

  public void add(CharacterType type) {
    this.type = type;
  }
}
