package net.sf.anathema.character.main.traits.caste;

import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.model.Hero;

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