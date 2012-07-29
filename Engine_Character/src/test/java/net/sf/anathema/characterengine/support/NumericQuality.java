package net.sf.anathema.characterengine.support;

import net.sf.anathema.characterengine.quality.Quality;

public class NumericQuality implements Quality {
  private final NumericValue value;

  public NumericQuality(NumericValue value) {
    this.value = value;
  }

  public boolean hasValue(NumericValue value) {
    return value.equals(this.value);
  }
}
