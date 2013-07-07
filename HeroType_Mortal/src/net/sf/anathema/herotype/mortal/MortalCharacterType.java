package net.sf.anathema.herotype.mortal;

import net.sf.anathema.character.main.template.magic.AbilityFavoringType;
import net.sf.anathema.character.main.template.magic.FavoringTraitType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.ICharacterType;
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
    return new AbilityFavoringType();
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