package net.sf.anathema.hero.template;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.dummy.template.DummyHeroTemplate;
import net.sf.anathema.hero.model.Hero;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NativeCharacterTypeTest {

  @Test
  public void returnsNativeTypeFromTemplate() throws Exception {
    CharacterType expectedType = new DummyMundaneCharacterType();
    Hero hero = mock(Hero.class);
    when(hero.getTemplate()).thenReturn(new DummyHeroTemplate());
    CharacterType type = NativeCharacterType.get(hero);
    assertThat(type, is(expectedType));

  }
}
