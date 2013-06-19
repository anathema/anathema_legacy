package net.sf.anathema.character.main.model.spells;

import net.sf.anathema.hero.model.Hero;

public class SpellsModelFetcher {

  public static SpellModel fetch(Hero hero) {
    return hero.getModel(SpellModel.ID);
  }
}
