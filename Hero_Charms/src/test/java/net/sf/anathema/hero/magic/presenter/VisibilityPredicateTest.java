package net.sf.anathema.hero.magic.presenter;

import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.charm.ICharmGroup;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.charms.display.special.VisibilityPredicate;
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
    CharacterType characterTypeForCharm = new DummyExaltCharacterType();
    Charm charm = createCharmForCharacterTypeFromGroup(characterTypeForCharm, ANY_ID);
    CharmIdMap map = createMapWithCharm(charm);
    ICharmGroup charmGroup = createACharmGroupThatContainsTheCharm(charm);
    selectGroup(charmGroup);
    VisibilityPredicate predicate = new VisibilityPredicate(map, informer);
    assertThat(predicate.apply(ANY_ID), is(true));
  }

  private ICharmGroup createACharmGroupThatContainsTheCharm(Charm charm) {
    ICharmGroup charmGroup = mock(ICharmGroup.class);
    when(charmGroup.isCharmFromGroup(charm)).thenReturn(true);
    return charmGroup;
  }

  private void selectGroup(ICharmGroup charmGroup) {
    when(informer.hasGroupSelected()).thenReturn(true);
    when(informer.getCurrentGroup()).thenReturn(charmGroup);
  }

  private CharmIdMap createMapWithCharm(Charm charm) {
    CharmIdMap map = mock(CharmIdMap.class);
    when(map.getCharmById(ANY_ID)).thenReturn(charm);
    return map;
  }
}