package net.sf.anathema.character.main.template.points;

import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.io.Serializable;

public class MultiplyRatingCosts extends ReflectionEqualsObject implements CurrentRatingCosts, Serializable {

  private final int factor;
  private final int initalCost;
  private final int summand;

  public MultiplyRatingCosts(int factor) {
    this(factor, Integer.MIN_VALUE);
  }

  public MultiplyRatingCosts(int factor, int initalCost) {
    this(factor, initalCost, 0);
  }

  public MultiplyRatingCosts(int factor, int initalCost, int summand) {
    this.factor = factor;
    this.initalCost = initalCost;
    this.summand = summand;
  }

  @Override
  public int getRatingCosts(int currentRating) {
    if (currentRating > 0) {
      return currentRating * factor + summand;
    }
    if (initalCost <= 0) {
      throw new UnsupportedOperationException("Illegal learning");
    }
    return initalCost;
  }

  @Override
  public String toString() {
    return "MultiplyRatingCost " + factor + "/" + summand + "/" + initalCost;
  }
}