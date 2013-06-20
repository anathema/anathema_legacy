package net.sf.anathema.character.presenter.magic.filter;

import net.sf.anathema.character.generic.traits.GenericTrait;

public class EssenceLevel {

  private int essence;

  public EssenceLevel(int essence) {
    this.essence = essence;
  }

  public boolean isGreaterOrEqualThan(GenericTrait trait) {
    return trait.getCurrentValue() <= essence;
  }

  public boolean isEqualTo(EssenceLevel other) {
    return essence == other.getValue();
  }

  public void setValueTo(EssenceLevel other) {
    this.essence = other.getValue();
  }

  public void setValueTo(int essenceValue) {
    this.essence = essenceValue;
  }

  public int getValue() {
    return essence;
  }
}
