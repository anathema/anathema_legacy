package net.sf.anathema.hero.magic.presenter;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.CharmIdMap;
import net.sf.anathema.character.main.magic.charms.ICharmGroup;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.presenter.magic.VisibilityPredicate;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.main.magic.charmtree.view.CharmGroupInformer;
import org.junit.Test;

import static net.sf.anathema.hero.magic.model.charms.CharmMother.createCharmForCharacterTypeFromGroup;
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
    ICharm charm = createCharmForCharacterTypeFromGroup(characterTypeForCharm, ANY_ID);
    CharmIdMap map = createMapWithCharm(charm);
    ICharmGroup charmGroup = createACharmGroupThatContainsTheCharm(charm);
    selectGroup(charmGroup);
    VisibilityPredicate predicate = new VisibilityPredicate(map, informer);
    assertThat(predicate.apply(ANY_ID), is(true));
  }

  private ICharmGroup createACharmGroupThatContainsTheCharm(ICharm charm) {
    ICharmGroup charmGroup = mock(ICharmGroup.class);
    when(charmGroup.isCharmFromGroup(charm)).thenReturn(true);
    return charmGroup;
  }

  private void selectGroup(ICharmGroup charmGroup) {
    when(informer.hasGroupSelected()).thenReturn(true);
    when(informer.getCurrentGroup()).thenReturn(charmGroup);
  }

  private CharmIdMap createMapWithCharm(ICharm charm) {
    CharmIdMap map = mock(CharmIdMap.class);
    when(map.getCharmById(ANY_ID)).thenReturn(charm);
    return map;
  }
}