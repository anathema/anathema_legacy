package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.dummy.DummyCharacterTypes;
import net.sf.anathema.character.generic.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.magic.dummy.DummyCharm;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CharmFinderTest {

  private DummyExaltCharacterType type = new DummyExaltCharacterType();
  private ICharmCache cache = mock(ICharmCache.class);
  private String id = "Dummy.Test";
  private ICharm sampleCharm = new DummyCharm(id);
  private DummyCharacterTypes characterTypes = new DummyCharacterTypes();
  private CharmFinder charmFinder = new CharmFinder(characterTypes, cache, id);

  @Before
  public void setUp() throws Exception {
    characterTypes.add(type);
  }

  @Test
  public void looksForMissingCharmsInMartialArts() throws Exception {
    when(cache.getCharms(type)).thenReturn(new ICharm[0]);
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new ICharm[]{sampleCharm});
    ICharm iCharm = charmFinder.find();
    assertThat(iCharm, is(sampleCharm));
  }

  @Test
  public void looksForMissingCharmsInCharacterTypeCharms() throws Exception {
    when(cache.getCharms(type)).thenReturn(new ICharm[]{sampleCharm});
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new ICharm[]{sampleCharm});
    ICharm iCharm = charmFinder.find();
    assertThat(iCharm, is(sampleCharm));
  }

  @Test
  public void prefersCharacterTypeOverMartialArts() throws Exception {
    when(cache.getCharms(type)).thenReturn(new ICharm[]{sampleCharm});
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new ICharm[]{sampleCharm});
    charmFinder.find();
    verify(cache, never()).getCharms(MARTIAL_ARTS);
  }
}