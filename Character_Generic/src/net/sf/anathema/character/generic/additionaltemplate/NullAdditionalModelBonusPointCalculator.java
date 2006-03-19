package net.sf.anathema.character.generic.additionaltemplate;

public class NullAdditionalModelBonusPointCalculator implements IAdditionalModelBonusPointCalculator {
  public void recalculate() {
    // Nothing to do
  }

  public int getBonusPointCost() {
    return 0;
  }

  public int getBonusPointsGranted() {
    return 0;
  }
}