package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.model.Hero;

public class SpellsModelFetcher {

  public static SpellModel fetch(Hero hero) {
    return hero.getModel(SpellModel.ID);
  }
}
