package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;


public interface IFavorableTraitCostCalculator {

  public void calculateCosts();

  public int getFreePointsSpent(boolean favored);

  public int getBonusPointsSpent();

  public int getFavoredPicksSpent();

  public FavorableTraitCost[] getCosts(IFavorableTrait trait);
}