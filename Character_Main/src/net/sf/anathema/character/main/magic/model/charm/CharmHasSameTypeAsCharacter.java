package net.sf.anathema.character.main.magic.model.charm;

import com.google.common.base.Predicate;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;

public class CharmHasSameTypeAsCharacter implements Predicate<Charm> {
  private Hero hero;

  public CharmHasSameTypeAsCharacter(Hero hero) {
    this.hero = hero;
  }

  @Override
  public boolean apply(Charm charm) {
    return isCharmForCharactersOwnType(charm);
  }


  private boolean isCharmForCharactersOwnType(Charm charm) {
    return NativeCharacterType.get(hero).equals(charm.getCharacterType());
  }
}