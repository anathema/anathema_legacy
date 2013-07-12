package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;

public class SpiritualExperienceCalculator {

  private final IExperiencePointCosts costs;

  public SpiritualExperienceCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  public int getEssenceCosts(Trait essence) {
    return getTraitRatingCosts(essence, costs.getEssenceCosts());
  }

  public int getVirtueCosts(Trait virtue) {
    return getTraitRatingCosts(virtue, costs.getVirtueCosts());
  }

  public int getWillpowerCosts(Trait willpower) {
    return getTraitRatingCosts(willpower, costs.getWillpowerCosts());
  }

  private int getTraitRatingCosts(Trait trait, CurrentRatingCosts ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCosts(trait, ratingCosts);
  }
}