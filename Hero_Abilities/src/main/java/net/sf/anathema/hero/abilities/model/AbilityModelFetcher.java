package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.model.Hero;

public class AbilityModelFetcher {

  public static AbilitiesModel fetch(Hero hero) {
    return hero.getModel(AbilitiesModel.ID);
  }
}
