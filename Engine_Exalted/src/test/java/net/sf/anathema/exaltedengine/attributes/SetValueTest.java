package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.exaltedengine.NumericValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SetValueTest {

  @Test
  public void changesAttributeValue() throws Exception {
    NumericValue newValue = new NumericValue(4);
    Name name = new Name("name");
    QualityKey qualityKey = new QualityKey(new Type("type"), name);
    DummyQualities qualities = new DummyQualities();
    Attribute attribute = new Attribute(new NumericValue(1), name);
    qualities.addQuality(qualityKey, attribute);
    new SetValue(qualityKey, newValue).execute(qualities);
    assertThat(attribute.hasValue(newValue), is(true));
  }
}
