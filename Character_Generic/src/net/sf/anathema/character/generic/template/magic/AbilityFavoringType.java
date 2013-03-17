package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class AbilityFavoringType implements FavoringTraitType {
  @Override
  public AbilityType[] getTraitTypesForGenericCharms() {
    return net.sf.anathema.character.generic.traits.types.AbilityType.values();
  }

  @Override
  public ITraitType getSpellFavoringType() {
    return net.sf.anathema.character.generic.traits.types.AbilityType.Occult;
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