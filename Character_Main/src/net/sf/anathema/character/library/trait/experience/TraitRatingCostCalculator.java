package net.sf.anathema.character.library.trait.experience;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.LearnTrait;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;

public class TraitRatingCostCalculator {

  public static int getTraitRatingCosts(LearnTrait trait, CurrentRatingCosts ratingCosts) {
    return getTraitRatingCosts(trait.getCreationValue(), trait.getExperiencedValue(), ratingCosts);
  }

  public static int getTraitRatingCosts(int valueToAchieveWithoutCost, int valueToPayFor, CurrentRatingCosts ratingCosts) {
    int traitCosts = 0;
    int currentRating = valueToAchieveWithoutCost;
    while (currentRating < valueToPayFor) {
      traitCosts += ratingCosts.getRatingCosts(currentRating);
      currentRating++;
    }
    return traitCosts;
  }
}