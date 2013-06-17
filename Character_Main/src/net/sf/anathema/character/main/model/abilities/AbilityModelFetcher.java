package net.sf.anathema.character.main.model.abilities;

import net.sf.anathema.character.main.hero.Hero;

public class AbilityModelFetcher {

  public static AbilityModel fetch(Hero hero) {
    return (AbilityModel) hero.getModel(AbilityModel.ID);
  }
}
