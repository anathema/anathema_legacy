package net.sf.anathema.character.db.magic;

import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;
import org.junit.Test;

import static net.sf.anathema.character.db.magic.Element.Fire;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ElementTest {

  @Test
  public void matchesIdentificateWithSameName() throws Exception {
    Identified identificate = new Identificate(Fire.name());
    assertThat(Fire.matches(identificate), is(true));
  }

  @Test
  public void doesNotMatchDifferentIdentificate() throws Exception {
    Identified identificate = new Identificate("Dawn");
    assertThat(Fire.matches(identificate), is(false));
  }
}
