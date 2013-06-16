package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class ValuedTraitType implements GenericTrait {

  public static final ValuedTraitType NULL_TYPE = new ValuedTraitType(null, 1);
  private final ITraitType traitType;
  private final int value;

  @Override
  public ITraitType getType() {
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