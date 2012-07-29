package net.sf.anathema.exaltedengine;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NumericValueTest {
  @Test
  public void isGreaterThanLesserValue() throws Exception {
    boolean greaterThan = new NumericValue(5).isGreaterThan(new NumericValue(4));
    assertThat(greaterThan, is(true));
  }

  @Test
  public void isNotGreaterThanEqualValue() throws Exception {
    boolean greaterThan = new NumericValue(5).isGreaterThan(new NumericValue(5));
    assertThat(greaterThan, is(false));
  }
}
