package net.sf.anathema.hero.points;

import net.sf.anathema.hero.model.Hero;

public class PointModelFetcher {

  public static PointsModel fetch(Hero hero) {
    return hero.getModel(PointsModel.ID);
  }
}
