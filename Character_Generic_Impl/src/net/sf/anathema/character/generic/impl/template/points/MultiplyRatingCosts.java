package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class MultiplyRatingCosts implements ICurrentRatingCosts {

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

  public int getRatingCosts(int currentRating) {
    if (currentRating > 0) {
      return currentRating * factor + summand;
    }
    if (initalCost <= 0) {
      throw new UnsupportedOperationException("Illegal learning"); //$NON-NLS-1$
    }
    return initalCost;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return factor;
  }

  @Override
  public String toString() {
    return "MultiplyRatingCost " + factor + "/" + summand + "/" + initalCost; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }
}