package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.magic.dummy.DummyCharm;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import net.sf.anathema.charmtree.view.CharmGroupInformer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisibilityPredicateTest {
  private static final String ANY_ID = "anyId";
  private CharmGroupInformer informer = mock(CharmGroupInformer.class);

  @Test
  public void charmIsVisibleIfCharacterTypesMatchButAreNotIdentical() throws Exception {
    ICharacterType characterTypeForCharm = new DummyExaltCharacterType();
    ICharacterType characterTypeForGroup = new DummyExaltCharacterType();
    DummyCharm charm = createCharmForCharacterType(characterTypeForCharm);
    ICharmIdMap map = createMapWithCharm(charm);
    when(informer.hasGroupSelected()).thenReturn(true);
    ICharmGroup charmGroup = mock(ICharmGroup.class);
    when(charmGroup.getId()).thenReturn(ANY_ID);
    when(charmGroup.getCharacterType()).thenReturn(characterTypeForGroup);
    when(informer.getCurrentGroup()).thenReturn(charmGroup);
    VisibilityPredicate predicate = new VisibilityPredicate(map, informer);
    assertThat(predicate.apply(ANY_ID), is(true));
  }

  private ICharmIdMap createMapWithCharm(DummyCharm charm) {
    ICharmIdMap map = mock(ICharmIdMap.class);
    when(map.getCharmById(ANY_ID)).thenReturn(charm);
    return map;
  }

  private DummyCharm createCharmForCharacterType(ICharacterType characterType) {
    DummyCharm charm = new DummyCharm(ANY_ID);
    charm.setCharacterType(characterType);
    charm.setGroupId(ANY_ID);
    return charm;
  }
}
