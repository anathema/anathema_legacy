package net.sf.anathema.herotype.mortal;

import net.sf.anathema.hero.template.magic.AbilityFavoringType;
import net.sf.anathema.hero.template.magic.FavoringTraitType;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.character.framework.type.RegisteredCharacterType;
import net.sf.anathema.framework.environment.dependencies.Weight;

@RegisteredCharacterType
@Weight(weight = 9)
public class MortalCharacterType implements CharacterType {

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