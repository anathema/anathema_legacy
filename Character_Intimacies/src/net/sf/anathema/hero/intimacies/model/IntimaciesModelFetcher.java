package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.hero.model.Hero;

public class IntimaciesModelFetcher {

  public static IntimaciesModel fetch(Hero hero) {
    return hero.getModel(IntimaciesModel.ID);
  }
}
