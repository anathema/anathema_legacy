package net.sf.anathema.hero.advance.experience.models;

import net.sf.anathema.hero.points.HeroModelExperienceCalculator;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.PointModelFetcher;

public class MiscellaneousExperienceModel extends AbstractIntegerValueModel {
  private final Hero hero;

  public MiscellaneousExperienceModel(Hero hero) {
    super("Experience", "Miscellaneous");
    this.hero = hero;
  }

  @Override
  public Integer getValue() {
    return getMiscCosts();
  }

  private int getMiscCosts() {
    int total = 0;
    for (HeroModelExperienceCalculator calculator : PointModelFetcher.fetch(hero).getExperienceCalculators()) {
      total += calculator.calculateCost();
    }
    total += ExperienceModelFetcher.fetch(hero).getExperiencePoints().getExtraSpendings();
    return total;
  }
}
