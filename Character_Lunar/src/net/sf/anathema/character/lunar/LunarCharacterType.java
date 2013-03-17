package net.sf.anathema.character.lunar;

import net.sf.anathema.character.generic.template.magic.AttributeFavoringType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 3)
public class LunarCharacterType implements ICharacterType {

  @Override
  public boolean isExaltType() {
    return true;
  }

  @Override
  public boolean isEssenceUser() {
    return true;
  }

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return new AttributeFavoringType();
  }

  @Override
  public String getId() {
    return "Lunar";
  }

  public boolean equals(Object other) {
    return other instanceof LunarCharacterType;
  }

  public int hashCode() {
    return 3;
  }
}