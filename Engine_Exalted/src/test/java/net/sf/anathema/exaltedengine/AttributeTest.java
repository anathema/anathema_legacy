package net.sf.anathema.exaltedengine;

import net.sf.anathema.characterengine.quality.Name;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttributeTest {
  Name name = new Name("name");

  @Test
  public void forwardsComparisonToValue() throws Exception {
    NumericValue ownValue = mock(NumericValue.class);
    NumericValue valueForComparison = mock(NumericValue.class);
    when(ownValue.isGreaterThan(valueForComparison)).thenReturn(true);
    boolean greaterThan = new Attribute(ownValue, name).isGreaterThan(valueForComparison);
    assertThat(greaterThan, is(true));
  }

  @Test
  public void identifiesOwnName() throws Exception {
    boolean hasName = new Attribute(new NumericValue(5), name).isCalled(new Name("name"));
    assertThat(hasName, is(true));
  }
}
