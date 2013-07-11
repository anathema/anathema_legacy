package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.model.Hero;

public class CharmsModelFetcher {

  public static CharmsModel fetch(Hero hero) {
    return hero.getModel(CharmsModel.ID);
  }
}
