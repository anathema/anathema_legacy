package net.sf.anathema.character.library.trait;

public class FavorableTraitCost {

  private int spentBonusPoints;
  private final int spentGeneralPoints;
  private final int spentFavoredPoints;
  private final int spentExtraPoints;

  public FavorableTraitCost(int spentBonusPoints, int spentGeneralPoints, int spentFavoredPoints)
  {
	  this(spentBonusPoints, spentGeneralPoints, spentFavoredPoints, 0);
  }
  
  public FavorableTraitCost(int spentBonusPoints, int spentGeneralPoints, int spentFavoredPoints,
		  int spentExtraPoints) {
    this.spentBonusPoints = spentBonusPoints;
    this.spentGeneralPoints = spentGeneralPoints;
    this.spentFavoredPoints = spentFavoredPoints;
    this.spentExtraPoints = spentExtraPoints;
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
  
  public int getExtraPointsCost()
  {
	  return spentExtraPoints;
  }
}