package net.sf.anathema.characterengine.support;

import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityListener;

public class NumericQuality implements Quality {
  private final NumericValue value;
  private final Announcer<QualityListener> announcer = Announcer.to(QualityListener.class);

  public NumericQuality(NumericValue value) {
    this.value = value;
  }

  public boolean hasValue(NumericValue value) {
    return value.equals(this.value);
  }

  public void changeBy(NumericValue modification) {
    value.changeBy(modification);
    announcer.announce().eventOccurred();
  }

  @Override
  public void registerObserver(QualityListener listener) {
    announcer.addListener(listener);
  }
}
