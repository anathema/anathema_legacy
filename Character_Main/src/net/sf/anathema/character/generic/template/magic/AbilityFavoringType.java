package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class AbilityFavoringType implements FavoringTraitType {
  @Override
  public AbilityType[] getTraitTypesForGenericCharms() {
    return AbilityType.values();
  }

  @Override
  public TraitType getSpellFavoringType() {
    return AbilityType.Occult;
  }
  @Override
  public String getId() {
    return "AbilityType";
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof AbilityFavoringType;
  }

  @Override
  public int hashCode() {
    return 1;
  }
}