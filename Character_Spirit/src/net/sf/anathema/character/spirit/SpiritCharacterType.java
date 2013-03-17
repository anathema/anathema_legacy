package net.sf.anathema.character.spirit;

import net.sf.anathema.character.generic.template.magic.AbilityFavoringType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 7)
public class SpiritCharacterType implements ICharacterType {

  @Override
  public boolean isExaltType() {
    return false;
  }

  @Override
  public boolean isEssenceUser() {
    return true;
  }

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return new AbilityFavoringType();
  }

  @Override
  public String getId() {
    return "Spirit";
  }

  public boolean equals(Object other) {
    return other instanceof SpiritCharacterType;
  }

  public int hashCode() {
    return 7;
  }
}