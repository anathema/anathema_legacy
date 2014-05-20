package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.character.main.library.trait.Trait;

public class TraitCostElement implements CostElement {

  private final Trait trait;

  public TraitCostElement(Trait trait) {
    this.trait = trait;
  }

  @Override
  public int getCalculationValue() {
    return trait.getCreationCalculationValue();
  }

  @Override
  public int getCalculationBase() {
    return trait.getZeroCalculationValue();
  }
}