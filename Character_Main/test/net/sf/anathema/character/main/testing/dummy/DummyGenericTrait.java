package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class DummyGenericTrait implements GenericTrait {

  private final ITraitType type;
  private final boolean isFavored;
  private final int currentValue;

  public DummyGenericTrait(ITraitType type, int currentValue) {
      this(type, currentValue, false);
  }

  public DummyGenericTrait(ITraitType type, int currentValue, boolean isFavored) {
    this.type = type;
    this.currentValue = currentValue;
    this.isFavored = isFavored;
  }

  @Override
  public ITraitType getType() {
    return type;
  }

  @Override
  public int getCurrentValue() {
    return currentValue;
  }

  @Override
  public boolean isCasteOrFavored() {
    return isFavored;
  }
}