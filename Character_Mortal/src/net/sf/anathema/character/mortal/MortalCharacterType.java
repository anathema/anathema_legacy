package net.sf.anathema.character.mortal;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterType
@Weight(weight = 9)
public class MortalCharacterType implements ICharacterType {

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
    return FavoringTraitType.AbilityType;
  }

  @Override
  public String getId() {
    return "Mortal";
  }

  public boolean equals(Object other) {
    return other instanceof MortalCharacterType;
  }

  public int hashCode() {
    return 9;
  }
}