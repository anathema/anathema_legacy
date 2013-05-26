package net.sf.anathema.exaltedengine.essence;

import net.sf.anathema.characterengine.engine.QualityFactory;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.exaltedengine.numericquality.NumericValue;

public class EssenceFactory implements QualityFactory {
  @Override
  public Quality create(Name name) {
    return new Essence(new NumericValue(1));
  }
}
