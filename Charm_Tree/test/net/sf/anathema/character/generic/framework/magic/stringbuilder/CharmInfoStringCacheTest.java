package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CharmInfoStringCacheTest {

  private final ICharm charm = new DummyCharm("id");

  @Test
  public void cachesStringsOnceRequested() throws Exception {
    ICharmInfoStringBuilder iCharmInfoStringBuilder = mock(ICharmInfoStringBuilder.class);
    CharmInfoStringCache cache = new CharmInfoStringCache(iCharmInfoStringBuilder);
    cache.getInfoString(charm, null);
    cache.getInfoString(charm, null);
    verify(iCharmInfoStringBuilder, times(1)).getInfoString(charm, null);
  }
}