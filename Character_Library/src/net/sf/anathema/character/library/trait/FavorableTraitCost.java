package net.sf.anathema.character.library.trait;

public class FavorableTraitCost {

  private int spentBonusPoints;
  private final int spentGeneralPoints;
  private final int spentFavoredPoints;

  public FavorableTraitCost(int spentBonusPoints, int spentGeneralPoints, int spentFavoredPoints) {
    this.spentBonusPoints = spentBonusPoints;
    this.spentGeneralPoints = spentGeneralPoints;
    this.spentFavoredPoints = spentFavoredPoints;
  }

  public int getBonusCost() {
    return spentBonusPoints;
  }

  public int getGeneralPointCost() {
    return spentGeneralPoints;
  }

  public int getFavoredPointCost() {
    return spentFavoredPoints;
  }
}