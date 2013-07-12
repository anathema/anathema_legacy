package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;

public class AbilityExperienceCalculator {

  private final IExperiencePointCosts costs;

  public AbilityExperienceCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  public int getAbilityCosts(Trait ability, final boolean favored) {
    return getTraitRatingCosts(ability, costs.getAbilityCosts(favored));
  }

  private int getTraitRatingCosts(Trait trait, CurrentRatingCosts ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCosts(trait, ratingCosts);
  }
}
