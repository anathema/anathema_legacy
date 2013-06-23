package net.sf.anathema.character.generic.additionaltemplate;

public class NullModelBonusPointCalculator implements HeroModelBonusPointCalculator {
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