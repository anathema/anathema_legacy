package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;

public class DummyCharacterTypes implements CharacterTypes {
  private ICharacterType type;

  @Override
  public ICharacterType findById(String id) {
    if (type.getId().equals(id)) {
      return type;
    }
    throw new IllegalArgumentException("No type defined for id:" + id); //$NON-NLS-1$
  }

  @Override
  public ICharacterType[] findAll() {
    return new ICharacterType[]{type};
  }

  public void add(ICharacterType type) {
    this.type = type;
  }
}
