package net.sf.anathema.exaltedengine.essence;

import net.sf.anathema.exaltedengine.numericquality.NumericValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EssenceValueTest {
  NumericValue ownValue = new NumericValue(1);
  Essence essence = new Essence(ownValue);

  @Test
  public void doesNotMismatchItsValue() throws Exception {
    NumericValue valueForComparison = new NumericValue(0);
    boolean isEqual = essence.hasValue(valueForComparison);
    assertThat(isEqual, is(false));
  }

  @Test
  public void changesItsValue() throws Exception {
    NumericValue newValue = new NumericValue(2);
    essence.changeValueTo(newValue);
    assertThat(essence.hasValue(newValue), is(true));
  }
}
