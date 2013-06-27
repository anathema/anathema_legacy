package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.template.magic.AbilityFavoringType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;

public class DummyMundaneCharacterType implements ICharacterType {

  @Override
  public boolean isExaltType() {
    return false;
  }

  @Override
  public boolean isEssenceUser() {
    return false;
  }

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return new AbilityFavoringType();
  }

  @Override
  public String getId() {
    return "Dummy";
  }

  public boolean equals(Object other) {
    return other instanceof DummyMundaneCharacterType;
  }
}