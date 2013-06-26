package net.sf.anathema.character.model.creation.bonus.attribute;

import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;

public interface IAttributeCostCalculator extends IFavorableTraitCostCalculator {
  void calculateAttributeCosts();

  int getBonusPoints();
}