package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.main.model.Hero;

public class ExperienceModelFetcher {

  public static ExperienceModel fetch(Hero hero) {
    return hero.getModel(ExperienceModel.ID);
  }
}
