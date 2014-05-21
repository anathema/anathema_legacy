package net.sf.anathema.hero.traits.model.types;

import net.sf.anathema.hero.traits.model.TraitType;

public class ValuedTraitType implements net.sf.anathema.hero.traits.model.ValuedTraitType {

  public static final ValuedTraitType NULL_TYPE = new ValuedTraitType(null, 1);
  private final TraitType traitType;
  private final int value;

  @Override
  public TraitType getType() {
    return traitType;
  }

  @Override
  public int getCurrentValue() {
    return value;
  }

  @Override
  public boolean isCasteOrFavored() {
    return false;
  }

  public ValuedTraitType(TraitType traitType, int value) {
    this.traitType = traitType;
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof ValuedTraitType)) {
      return false;
    }
    ValuedTraitType otherType = (ValuedTraitType) obj;
    if (otherType.getType() == null) {
      return false;
    }
    return otherType.getType().equals(traitType) && otherType.getCurrentValue() == value;
  }

  @Override
  public int hashCode() {
    return traitType.hashCode() + value * 17;
  }
}