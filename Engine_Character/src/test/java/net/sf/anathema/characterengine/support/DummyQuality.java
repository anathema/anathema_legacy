package net.sf.anathema.characterengine.support;

import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityListener;

public class DummyQuality implements Quality {
  @Override
  public void registerObserver(QualityListener listener) {
    //nothing to do
  }

  @Override
  public Quality copy() {
    return new DummyQuality();
  }
}
