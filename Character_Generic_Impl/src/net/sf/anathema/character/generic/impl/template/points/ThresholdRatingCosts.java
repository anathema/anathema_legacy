package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;

public class ThresholdRatingCosts implements ICurrentRatingCosts {

  private final int lowCost;
  private final int highCost;

  public ThresholdRatingCosts(int lowCost, int highCost) {
    this.lowCost = lowCost;
    this.highCost = highCost;
  }

  public int getRatingCosts(int currentRating) {
    return currentRating < 3 ? lowCost : highCost;
  }
}