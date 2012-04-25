package net.sf.anathema.character.generic.additionaltemplate;

public class NullAdditionalModelBonusPointCalculator implements IAdditionalModelBonusPointCalculator {
  @Override
  public void recalculate() {
    // Nothing to do
  }

  @Override
  public int getBonusPointCost() {
    return 0;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }
}