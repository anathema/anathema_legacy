package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class ValuedTraitType implements IGenericTrait {

  public static final ValuedTraitType NULL_TYPE = new ValuedTraitType(null, 1);
  private final ITraitType traitType;
  private final int value;

  public ITraitType getType() {
    return traitType;
  }

  public int getCurrentValue() {
    return value;
  }

  public ValuedTraitType(ITraitType traitType, int value) {
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