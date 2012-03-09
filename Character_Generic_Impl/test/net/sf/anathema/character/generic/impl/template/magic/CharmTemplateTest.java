package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.impl.magic.UniqueCharmType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CharmTemplateTest {

  @Test
  public void hasUniqueCharmsIfTypeIsSet() throws Exception {
    CharmTemplate template = new CharmTemplate(null, null, new UniqueCharmType("x", "y", "z"));
    assertThat(template.hasUniqueCharms(), is(true));
  }

  @Test
  public void hasNoUniqueCharmsOtherwise() throws Exception {
    CharmTemplate template = new CharmTemplate(null, null);
    assertThat(template.hasUniqueCharms(), is(false));
  }
}
