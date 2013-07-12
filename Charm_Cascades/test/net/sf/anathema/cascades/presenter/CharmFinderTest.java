package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.parser.charms.ICharmCache;
import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterTypes;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities.MARTIAL_ARTS;
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
  private Charm sampleCharm = new DummyCharm(id);
  private DummyCharacterTypes characterTypes = new DummyCharacterTypes();
  private CharmFinder charmFinder = new CharmFinder(characterTypes, cache, id);

  @Before
  public void setUp() throws Exception {
    characterTypes.add(type);
  }

  @Test
  public void looksForMissingCharmsInMartialArts() throws Exception {
    when(cache.getCharms(type)).thenReturn(new Charm[0]);
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new Charm[]{sampleCharm});
    Charm charm = charmFinder.find();
    assertThat(charm, is(sampleCharm));
  }

  @Test
  public void looksForMissingCharmsInCharacterTypeCharms() throws Exception {
    when(cache.getCharms(type)).thenReturn(new Charm[]{sampleCharm});
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new Charm[]{sampleCharm});
    Charm charm = charmFinder.find();
    assertThat(charm, is(sampleCharm));
  }

  @Test
  public void prefersCharacterTypeOverMartialArts() throws Exception {
    when(cache.getCharms(type)).thenReturn(new Charm[]{sampleCharm});
    when(cache.getCharms(MARTIAL_ARTS)).thenReturn(new Charm[]{sampleCharm});
    charmFinder.find();
    verify(cache, never()).getCharms(MARTIAL_ARTS);
  }
}