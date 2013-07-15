package net.sf.anathema.hero.experience;

import net.sf.anathema.hero.model.Hero;

public class ExperienceModelFetcher {

  public static ExperienceModel fetch(Hero hero) {
    return hero.getModel(ExperienceModel.ID);
  }
}
