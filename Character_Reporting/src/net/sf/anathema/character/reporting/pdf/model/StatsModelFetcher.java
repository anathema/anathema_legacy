package net.sf.anathema.character.reporting.pdf.model;

import net.sf.anathema.hero.model.Hero;

public class StatsModelFetcher {

  public static StatsModel fetch(Hero hero) {
    return hero.getModel(StatsModel.ID);
  }
}
