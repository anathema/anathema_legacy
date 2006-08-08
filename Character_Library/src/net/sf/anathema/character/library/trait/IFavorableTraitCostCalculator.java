package net.sf.anathema.character.library.trait;


public interface IFavorableTraitCostCalculator {

  public void calculateCosts();

  public int getFreePointsSpent(boolean favored);

  public int getBonusPointsSpent();

  public int getFavoredPicksSpent();

  public FavorableTraitCost getCosts(IFavorableModifiableTrait trait);
}