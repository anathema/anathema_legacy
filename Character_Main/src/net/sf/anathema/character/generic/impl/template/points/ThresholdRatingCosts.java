package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.io.Serializable;

public class ThresholdRatingCosts extends ReflectionEqualsObject implements CurrentRatingCosts, Serializable {

  private final int lowCost;
  private final int highCost;
  private final int threshold;

  public ThresholdRatingCosts(int lowCost, int highCost, int threshold) {
    this.lowCost = lowCost;
    this.highCost = highCost;
    this.threshold = threshold;
  }

  @Override
  public int getRatingCosts(int currentRating) {
    return currentRating < threshold ? lowCost : highCost;
  }
}