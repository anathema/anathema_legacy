package net.sf.anathema.character.impl.model.creation.bonus.trait;

import net.sf.anathema.character.impl.model.creation.bonus.basic.ICostElement;
import net.sf.anathema.character.library.trait.ITrait;

public class TraitCostElement implements ICostElement {
  
  private final ITrait trait;

  public TraitCostElement(ITrait trait) {
    this.trait = trait;
  }

  public int getCalculationValue() {
    return trait.getCalculationValue();
  }

  public int getZeroCalculationValue() {
    return trait.getZeroCalculationValue();
  }
}