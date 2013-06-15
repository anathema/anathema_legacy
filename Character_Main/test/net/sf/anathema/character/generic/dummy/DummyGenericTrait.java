package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class DummyGenericTrait implements IGenericTrait {

  private final ITraitType type;
  private final int currentValue;

  public DummyGenericTrait(ITraitType type, int currentValue) {
    this.type = type;
    this.currentValue = currentValue;
  }
  
  @Override
  public ITraitType getType() {
    return type;
  }

  @Override
  public int getCurrentValue() {
    return currentValue;
  }
}