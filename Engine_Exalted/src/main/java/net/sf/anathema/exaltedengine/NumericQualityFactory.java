package net.sf.anathema.exaltedengine;

import net.sf.anathema.characterengine.engine.QualityFactory;
import net.sf.anathema.characterengine.quality.Quality;

public class NumericQualityFactory implements QualityFactory {
  private final int startValue;

  public NumericQualityFactory(int startValue) {
    this.startValue = startValue;
  }

  @Override
  public Quality create() {
    return new NumericQuality(new NumericValue(startValue));
  }
}
