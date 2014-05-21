package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.charms.model.CharmHasSameTypeAsCharacter;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.dummy.template.DummyHeroTemplate;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.hero.model.Hero;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmHasSameTypeAsCharacterTest {

  @Test
  public void identifiesMatchingCharacterTypesIfTheyAreNotIdentical() throws Exception {
    DummyCharm charm = createCharmForCharacterType(new DummyMundaneCharacterType());
    Hero hero = createHeroWithType(new DummyMundaneCharacterType());
    CharmHasSameTypeAsCharacter predicate = new CharmHasSameTypeAsCharacter(hero);
    assertThat(predicate.apply(charm), is(true));
  }

  private Hero createHeroWithType(CharacterType characterType) {
    Hero hero = mock(Hero.class);
    DummyHeroTemplate template = new DummyHeroTemplate();
    template.setCharacterType(characterType);
    when(hero.getTemplate()).thenReturn(template);
    return hero;
  }

  private DummyCharm createCharmForCharacterType(CharacterType characterTypeForCharm) {
    DummyCharm charm = new DummyCharm();
    charm.setCharacterType(characterTypeForCharm);
    return charm;
  }
}