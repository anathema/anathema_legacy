package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class FixedValueRatingCosts implements CurrentRatingCosts {

  private final int value;

  public FixedValueRatingCosts(int value) {
    this.value = value;
  }

  @Override
  public int getRatingCosts(int currentRating) {
    return value;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return value;
  }
}