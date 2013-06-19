package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.additionaltemplate.HeroModelExperienceCalculator;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.hero.points.PointModelFetcher;

public class MiscellaneousExperienceModel extends AbstractIntegerValueModel {
  private final ICharacter character;

  public MiscellaneousExperienceModel(ICharacter character) {
    super("Experience", "Miscellaneous");
    this.character = character;
  }

  @Override
  public Integer getValue() {
    return getMiscCosts();
  }

  private int getMiscCosts() {
    int total = 0;
    for (HeroModelExperienceCalculator calculator : PointModelFetcher.fetch(character).getExperienceCalculators()) {
      total += calculator.calculateCost();
    }
    total += ExperienceModelFetcher.fetch(character).getExperiencePoints().getExtraSpendings();
    return total;
  }
}
