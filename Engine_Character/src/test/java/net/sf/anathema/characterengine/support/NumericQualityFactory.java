package net.sf.anathema.characterengine.support;

import net.sf.anathema.characterengine.engine.QualityFactory;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;

public class NumericQualityFactory implements QualityFactory {
  private final int startValue;

  public NumericQualityFactory(int startValue) {
    this.startValue = startValue;
  }

  @Override
  public Quality create(Name name) {
    return new NumericQuality(new NumericValue(startValue));
  }
}