package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.exaltedengine.NumericValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AttributeFactoryTest {
  AttributeFactory factory = new AttributeFactory();

  @Test
  public void attributesDoNotInfluenceEachOther() throws Exception {
    Attribute strength = (Attribute) factory.create(new Name("Strength"));
    Attribute stamina = (Attribute) factory.create(new Name("Stamina"));
    strength.changeValueTo(new NumericValue(2));
    assertThat(stamina.hasValue(new NumericValue(1)), is(true));
  }
}