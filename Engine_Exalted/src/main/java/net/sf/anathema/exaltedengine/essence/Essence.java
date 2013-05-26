package net.sf.anathema.exaltedengine.essence;

import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.exaltedengine.numericquality.NumericValue;
import net.sf.anathema.exaltedengine.numericquality.QualityWithValue;

public class Essence implements Quality, QualityWithValue {
  private NumericValue ownValue;

  public Essence(NumericValue startValue) {
    this.ownValue = startValue;
  }

  @Override
  public void registerObserver(QualityListener listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Quality copy() {
    return new Essence(ownValue.copy());
  }

  @Override
  public boolean hasValue(NumericValue numericValue) {
    return this.ownValue.equals(numericValue);
  }

  @Override
  public void changeValueTo(NumericValue numericValue) {
    ownValue.changeTo(numericValue);
  }
}