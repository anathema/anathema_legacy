package net.sf.anathema.character.main.model.spells;

import net.sf.anathema.character.main.hero.Hero;

public class SpellsModelFetcher {

  public static SpellModel fetch(Hero hero) {
    return hero.getModel(SpellModel.ID);
  }
}
