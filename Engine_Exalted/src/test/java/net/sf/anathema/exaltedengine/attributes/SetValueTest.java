package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.exaltedengine.NumericValue;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SetValueTest {
  Name name = new Name("name");
  QualityKey qualityKey = new QualityKey(new Type("type"), name);
  DummyQualities qualities = new DummyQualities();
  Attribute attribute = new Attribute(new NumericValue(1), name);

  @Before
  public void setUp() throws Exception {
    qualities.addQuality(qualityKey, attribute);
  }

  @Test
  public void changesAttributeValue() throws Exception {
    changeValueTo(new NumericValue(4));
    assertThat(attribute.hasValue(new NumericValue(4)), is(true));
  }

  @Test
  public void doesNotChangeAttributeValueBelowMinimum() throws Exception {
    changeValueTo(new NumericValue(0));
    assertThat(attribute.hasValue(new NumericValue(1)), is(true));
  }

  private void changeValueTo(NumericValue newValue) {
    new SetValue(qualityKey, newValue).execute(qualities);
  }
}
