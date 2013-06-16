package net.sf.anathema.character.library.trait;

public interface IFavorableTraitCostCalculator {

  void calculateCosts();

  int getFreePointsSpent(boolean favored);

  int getExtraFavoredDotsSpent();

  int getExtraGenericDotsSpent();

  int getBonusPointsSpent();

  int getFavoredPicksSpent();

  FavorableTraitCost[] getCosts(Trait trait);
}