package net.sf.anathema.herotype.solar.model;

import net.sf.anathema.character.main.template.magic.AbilityFavoringType;
import net.sf.anathema.character.main.template.magic.FavoringTraitType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.RegisteredCharacterType;
import net.sf.anathema.initialization.reflections.Weight;

@RegisteredCharacterType
@Weight(weight = 1)
public class SolarCharacterType implements CharacterType {

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
    return new AbilityFavoringType();
  }

  @Override
  public String getId() {
    return "Solar";
  }

  public boolean equals(Object other) {
    return other instanceof SolarCharacterType;
  }

  public int hashCode() {
    return 1;
  }
}