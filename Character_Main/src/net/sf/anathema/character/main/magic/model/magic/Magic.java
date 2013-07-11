package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.hero.model.Hero;

public interface Magic extends IMagicData {

  boolean isFavored(Hero hero);
}