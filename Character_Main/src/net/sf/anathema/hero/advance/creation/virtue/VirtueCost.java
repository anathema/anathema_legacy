package net.sf.anathema.hero.advance.creation.virtue;

public class VirtueCost implements IVirtueCost {

  private final int dotsSpent;
  private final int bonusPointsSpent;

  public VirtueCost(int dotsSpent, int bonusPointsSpent) {
    this.dotsSpent = dotsSpent;
    this.bonusPointsSpent = bonusPointsSpent;
  }

  @Override
  public int getBonusPointsSpent() {
    return bonusPointsSpent;
  }

  @Override
  public int getDotsSpent() {
    return dotsSpent;
  }
}