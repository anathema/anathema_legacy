package net.sf.anathema.character.impl.model.creation.bonus.trait;

import net.sf.anathema.character.impl.model.creation.bonus.basic.ICostElement;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public class TraitCostElement implements ICostElement {
  
  private final IDefaultTrait trait;

  public TraitCostElement(IDefaultTrait trait) {
    this.trait = trait;
  }

  public int getCalculationValue() {
    return trait.getCalculationValue();
  }

  public int getZeroCalculationValue() {
    return trait.getZeroCalculationValue();
  }
}