package net.sf.anathema.character.impl.model.creation.bonus.trait;

import net.sf.anathema.character.impl.model.creation.bonus.basic.ICostElement;
import net.sf.anathema.character.library.trait.IDefaultTrait;

public class TraitCostElement implements ICostElement {

  private final IDefaultTrait trait;

  public TraitCostElement(IDefaultTrait trait) {
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