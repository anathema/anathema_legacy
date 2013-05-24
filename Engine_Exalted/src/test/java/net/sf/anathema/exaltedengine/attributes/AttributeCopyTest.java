package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.exaltedengine.numericquality.NumericValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AttributeCopyTest {
  Name name = new Name("name");
  NumericValue ownValue = new NumericValue(1);
  Attribute attribute = new Attribute(ownValue, name);

  @Test
  public void createCopyWithSameName() throws Exception {
    boolean hasName = ((Attribute) attribute.copy()).isCalled(new Name("name"));
    assertThat(hasName, is(true));
  }

  @Test
  public void createCopyWithSameValue() throws Exception {
    boolean hasValue = ((Attribute) attribute.copy()).hasValue(ownValue);
    assertThat(hasValue, is(true));
  }

  @Test
  public void createsAnIndependentCopy() throws Exception {
    Attribute copy = (Attribute) attribute.copy();
    copy.changeValueTo(new NumericValue(5));
    assertThat(attribute.hasValue(new NumericValue(1)), is(true));
  }
}