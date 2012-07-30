package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.exaltedengine.NumericValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SetValueTest {

  @Test
  public void changesAttributeValue() throws Exception {
    NumericValue newValue = new NumericValue(4);
    SetValue setValue = new SetValue(newValue);
    Attribute attribute = new Attribute(new NumericValue(1), new Name("name"));
    setValue.execute(attribute);
    assertThat(attribute.hasValue(newValue), is(true));
  }
}
