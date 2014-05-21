package net.sf.anathema.hero.attributes.advance.experience;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.advance.TraitRatingCostCalculator;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;

public class AttributesExperienceCalculator {

  private final IExperiencePointCosts costs;

  public AttributesExperienceCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  public int getAttributeCosts(Trait trait, boolean favored) {
    return getTraitRatingCosts(trait, costs.getAttributeCosts(favored));
  }

  private int getTraitRatingCosts(Trait trait, CurrentRatingCosts ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCosts(trait, ratingCosts);
  }
}
