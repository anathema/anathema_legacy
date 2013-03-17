package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class AttributeFavoringType implements FavoringTraitType {
  @Override
  public AttributeType[] getTraitTypesForGenericCharms() {
    return AttributeType.values();
  }

  @Override
  public ITraitType getSpellFavoringType() {
    return AttributeType.Intelligence;
  }

  @Override
  public String getId() {
    return "AttributeType";
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof AttributeFavoringType;
  }

  @Override
  public int hashCode() {
    return 2;
  }
}