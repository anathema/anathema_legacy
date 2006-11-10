package net.sf.anathema.character.impl.model.creation.bonus.virtue;

public class VirtueCost implements IVirtueCost {

  private final int dotsSpent;
  private final int bonusPointsSpent;

  public VirtueCost(int dotsSpent, int bonusPointsSpent) {
    this.dotsSpent = dotsSpent;
    this.bonusPointsSpent = bonusPointsSpent;
  }

  public int getBonusPointsSpent() {
    return bonusPointsSpent;
  }

  public int getDotsSpent() {
    return dotsSpent;
  }
}