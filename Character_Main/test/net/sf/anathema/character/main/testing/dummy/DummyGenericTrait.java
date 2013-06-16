package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;

public class DummyGenericTrait implements GenericTrait {

  private final TraitType type;
  private final boolean isFavored;
  private final int currentValue;

  public DummyGenericTrait(TraitType type, int currentValue) {
      this(type, currentValue, false);
  }

  public DummyGenericTrait(TraitType type, int currentValue, boolean isFavored) {
    this.type = type;
    this.currentValue = currentValue;
    this.isFavored = isFavored;
  }

  @Override
  public TraitType getType() {
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