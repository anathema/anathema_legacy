package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.dummy.DummyCasteType;
import org.junit.Test;

import static net.sf.anathema.character.generic.impl.magic.charm.special.Element.Fire;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ElementTest {

  @Test
  public void matchesAspectWithSameName() throws Exception {
    ICasteType castetype = new DummyCasteType(Fire.name());
    assertThat(Fire.matches(castetype), is(true));
  }

  @Test
  public void doesNotMatchDifferentCaste() throws Exception {
    ICasteType castetype = new DummyCasteType("Dawn");
    assertThat(Fire.matches(castetype), is(false));
  }
}
