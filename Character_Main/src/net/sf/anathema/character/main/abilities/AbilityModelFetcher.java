package net.sf.anathema.character.main.abilities;

import net.sf.anathema.character.main.model.Hero;

public class AbilityModelFetcher {

  public static AbilityModel fetch(Hero hero) {
    return (AbilityModel) hero.getModel(AbilityModel.ID);
  }
}
