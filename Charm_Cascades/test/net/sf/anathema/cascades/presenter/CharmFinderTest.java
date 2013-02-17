package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import org.junit.Test;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.character.generic.type.CharacterType.SOLAR;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CharmFinderTest {

  private ICharmCache cache = mock(ICharmCache.class);
  private String id = "Solar.Test";
  private ICharm sampleCharm = new DummyCharm(id);
  private CharmFinder charmFinder = new CharmFinder(cache, id);

  @Test
  public void looksForMissingCharmsInMartialArts() throws Exception {
    when(cache.getCharms(SOLAR)).thenReturn(new ICharm[0]);
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new ICharm[]{sampleCharm});
    ICharm iCharm = charmFinder.find();
    assertThat(iCharm, is(sampleCharm));
  }

  @Test
  public void looksForMissingCharmsInCharacterTypeCharms() throws Exception {
    when(cache.getCharms(SOLAR)).thenReturn(new ICharm[]{sampleCharm});
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new ICharm[]{sampleCharm});
    ICharm iCharm = charmFinder.find();
    assertThat(iCharm, is(sampleCharm));
  }

  @Test
  public void prefersCharacterTypeOverMartialArts() throws Exception {
    when(cache.getCharms(SOLAR)).thenReturn(new ICharm[]{sampleCharm});
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new ICharm[]{sampleCharm});
    charmFinder.find();
    verify(cache, never()).getCharms(MARTIAL_ARTS);
  }
}
