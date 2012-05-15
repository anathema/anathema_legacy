package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;

public interface IFavorableTraitCostCalculator {

  void calculateCosts();

  int getFreePointsSpent(boolean favored);
  
  int getExtraFavoredDotsSpent();
  
  int getExtraGenericDotsSpent();

  int getBonusPointsSpent();

  int getFavoredPicksSpent();

  FavorableTraitCost[] getCosts(IFavorableTrait trait);
}