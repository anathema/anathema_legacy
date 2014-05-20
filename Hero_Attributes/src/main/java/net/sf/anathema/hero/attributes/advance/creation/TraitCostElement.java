package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.hero.traits.advance.TraitCalculationUtilities;

public class TraitCostElement implements CostElement {

  private final Trait trait;

  public TraitCostElement(Trait trait) {
    this.trait = trait;
  }

  @Override
  public int getCalculationValue() {
    return TraitCalculationUtilities.getCreationCalculationValue(trait);
  }

  @Override
  public int getCalculationBase() {
    return trait.getZeroCalculationValue();
  }
}