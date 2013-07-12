package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.character.main.library.trait.Trait;

public class TraitCostElement implements ICostElement {

  private final Trait trait;

  public TraitCostElement(Trait trait) {
    this.trait = trait;
  }

  @Override
  public int getCalculationValue() {
    return trait.getCalculationValue();
  }

  @Override
  public int getZeroCalculationValue() {
    return trait.getZeroCalculationValue();
  }
}