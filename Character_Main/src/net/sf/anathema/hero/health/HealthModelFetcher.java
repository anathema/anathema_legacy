package net.sf.anathema.hero.health;

import net.sf.anathema.hero.model.Hero;

public class HealthModelFetcher {

  public static HealthModel fetch(Hero hero) {
    return (HealthModel) hero.getModel(HealthModel.ID);
  }
}
