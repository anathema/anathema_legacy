package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.hero.model.Hero;

public interface IMagic extends IMagicData {

  void accept(IMagicVisitor visitor);

  boolean isFavored(Hero hero);
}