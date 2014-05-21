package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

public class DummyGenericTrait implements ValuedTraitType {

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