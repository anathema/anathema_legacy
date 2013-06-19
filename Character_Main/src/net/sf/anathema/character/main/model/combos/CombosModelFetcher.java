package net.sf.anathema.character.main.model.combos;

import net.sf.anathema.hero.model.Hero;

public class CombosModelFetcher {

  public static CombosModel fetch(Hero hero) {
    return hero.getModel(CombosModel.ID);
  }
}
