package net.sf.anathema.hero.template.points;

import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.io.Serializable;

public class FixedValueRatingCosts extends ReflectionEqualsObject implements CurrentRatingCosts, Serializable {

  private final int value;

  public FixedValueRatingCosts(int value) {
    this.value = value;
  }

  @Override
  public int getRatingCosts(int currentRating) {
    return value;
  }
}