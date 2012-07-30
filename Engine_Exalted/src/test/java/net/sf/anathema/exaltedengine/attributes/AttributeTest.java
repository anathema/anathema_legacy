package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.exaltedengine.NumericValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class AttributeTest {
  Name name = new Name("name");
  NumericValue ownValue = new NumericValue(1);
  Attribute attribute = new Attribute(ownValue, name);

  @Test
  public void forwardsComparisonToValue() throws Exception {
    NumericValue valueForComparison = new NumericValue(0);
    boolean greaterThan = attribute.isGreaterThan(valueForComparison);
    assertThat(greaterThan, is(true));
  }

  @Test
  public void identifiesOwnName() throws Exception {
    boolean hasName = attribute.isCalled(new Name("name"));
    assertThat(hasName, is(true));
  }

  @Test
  public void notifiesListenersWhenValueChanges() throws Exception {
    QualityListener listener = mock(QualityListener.class);
    attribute.registerObserver(listener);
    attribute.changeValueTo(new NumericValue(2));
    verify(listener).eventOccurred();
  }

  @Test
  public void doesNothingIfValueDoesNotChange() throws Exception {
    QualityListener listener = mock(QualityListener.class);
    attribute.registerObserver(listener);
    attribute.changeValueTo(new NumericValue(1));
    verifyZeroInteractions(listener);
  }
}
