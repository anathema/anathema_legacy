package net.sf.anathema.character.main.model.abilities;

import net.sf.anathema.hero.model.Hero;

public class AbilityModelFetcher {

  public static AbilityModel fetch(Hero hero) {
    return (AbilityModel) hero.getModel(AbilityModel.ID);
  }
}
