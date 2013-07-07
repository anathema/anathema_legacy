package net.sf.anathema.character.main.magic.model.charm;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;

public class CharmHasSameTypeAsCharacter implements Predicate<ICharm> {
  private Hero hero;

  public CharmHasSameTypeAsCharacter(Hero hero) {
    this.hero = hero;
  }

  @Override
  public boolean apply(ICharm charm) {
    return isCharmForCharactersOwnType(charm);
  }


  private boolean isCharmForCharactersOwnType(ICharm charm) {
    return NativeCharacterType.get(hero).equals(charm.getCharacterType());
  }
}