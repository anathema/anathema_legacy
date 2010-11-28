package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;

import org.apache.commons.lang.builder.EqualsBuilder;

public class FixedValueRatingCosts implements ICurrentRatingCosts {

  private final int value;

  public FixedValueRatingCosts(int value) {
    this.value = value;
  }

  public int getRatingCosts(int currentRating) {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return value;
  }
}