package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import org.junit.Test;

import static net.sf.anathema.character.generic.impl.magic.charm.special.Element.Fire;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ElementTest {

  @Test
  public void matchesIdentificateWithSameName() throws Exception {
    IIdentificate identificate = new Identificate(Fire.name());
    assertThat(Fire.matches(identificate), is(true));
  }

  @Test
  public void doesNotMatchDifferentIdentificate() throws Exception {
    IIdentificate identificate = new Identificate("Dawn");
    assertThat(Fire.matches(identificate), is(false));
  }
}
