package net.sf.anathema.test.character.dummy;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.IModifiableGenericTrait;

public class DummyModifiableGenericTrait implements IModifiableGenericTrait {

  private int currentValue;
  private final ITraitType type;
  
  public DummyModifiableGenericTrait(ITraitType type) {
    this(type, 0);
  }

  public DummyModifiableGenericTrait(ITraitType type, int value) {
    this.type = type;
    this.currentValue = value;
  }

  public void setCurrentValue(int value) {
    this.currentValue = value;
  }

  public ITraitType getType() {
    return type;
  }

  public int getCurrentValue() {
    return currentValue;
  }
}