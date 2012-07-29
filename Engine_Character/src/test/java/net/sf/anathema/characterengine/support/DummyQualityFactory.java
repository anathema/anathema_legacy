package net.sf.anathema.characterengine.support;

import net.sf.anathema.characterengine.engine.QualityFactory;
import net.sf.anathema.characterengine.quality.Quality;

public class DummyQualityFactory implements QualityFactory {
  @Override
  public Quality create() {
    return new DummyQuality();
  }
}