package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.exaltedengine.numericquality.MaximumQualityValue;
import net.sf.anathema.exaltedengine.numericquality.NumericValue;
import org.junit.Test;

import static net.sf.anathema.characterengine.persona.Permission.Denied;
import static net.sf.anathema.characterengine.persona.Permission.Granted;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MaximumQualityValueTest {
  public static final int MAXIMUM = 5;
  MaximumQualityValue rule = new MaximumQualityValue(MAXIMUM);

  @Test
  public void attributesCanReachTheMaximumValue() throws Exception {
    Attribute attributeAtMax = createAttribute(MAXIMUM);
    Permission permission = rule.permits(attributeAtMax);
    assertThat(permission, is(Granted));
  }

  @Test
  public void attributesCannotExceedTheMaximumValue() throws Exception {
    Attribute attributeAboveMax = createAttribute(MAXIMUM + 1);
    Permission permission = rule.permits(attributeAboveMax);
    assertThat(permission, is(Denied));
  }

  private Attribute createAttribute(int value) {
    return new Attribute(new NumericValue(value), new Name("Strength"));
  }
}