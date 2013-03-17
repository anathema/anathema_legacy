package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public class YoziFavoringType implements FavoringTraitType {
  @Override
  public YoziType[] getTraitTypesForGenericCharms() {
    return net.sf.anathema.character.generic.traits.types.YoziType.values();
  }

  @Override
  public ITraitType getSpellFavoringType() {
    return net.sf.anathema.character.generic.traits.types.AbilityType.Occult;
  }

  @Override
  public String getId() {
    return "YoziType";
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof YoziFavoringType;
  }

  @Override
  public int hashCode() {
    return 4;
  }
}