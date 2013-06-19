package net.sf.anathema.character.generic.impl.traits.caste;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.main.hero.Hero;

public class StaticCasteMinimum implements ICasteTraitMinimum {

  private final CasteType caste;
  private int minimum;

  public StaticCasteMinimum(CasteType casteType, int minimum) {
    this.caste = casteType;
    this.minimum = minimum;
  }

  @Override
  public CasteType getCaste() {
    return caste;
  }

  @Override
  public int getMinimumValue(Hero hero) {
    return minimum;
  }
}