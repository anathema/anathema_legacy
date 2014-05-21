package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.advance.TraitCalculationUtilities;
import net.sf.anathema.hero.traits.advance.TraitListCreationData;

public class TraitCostElement implements CostElement {

  private final Trait trait;
  private TraitListCreationData creationData;

  public TraitCostElement(Trait trait, TraitListCreationData creationData) {
    this.trait = trait;
    this.creationData = creationData;
  }

  @Override
  public int getCalculationValue() {
    return TraitCalculationUtilities.getCreationCalculationValue(trait, creationData);
  }

  @Override
  public int getCalculationBase() {
    return creationData.getCalculationBase(trait.getType());
  }
}