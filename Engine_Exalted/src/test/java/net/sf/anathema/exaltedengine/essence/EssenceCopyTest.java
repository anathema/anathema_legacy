package net.sf.anathema.exaltedengine.essence;

import net.sf.anathema.exaltedengine.numericquality.NumericValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EssenceCopyTest {
  NumericValue ownValue = new NumericValue(1);
  Essence essence = new Essence(ownValue);

  @Test
  public void createCopyWithSameValue() throws Exception {
    boolean hasValue = ((Essence) essence.copy()).hasValue(ownValue);
    assertThat(hasValue, is(true));
  }

  @Test
  public void createsAnIndependentCopy() throws Exception {
    Essence copy = (Essence) essence.copy();
    copy.changeValueTo(new NumericValue(5));
    assertThat(essence.hasValue(new NumericValue(1)), is(true));
  }
}