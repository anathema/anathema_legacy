package net.sf.anathema.character.library.trait.experience;

import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.library.trait.Trait;

public class TraitRatingCostCalculator {

  public static int getTraitRatingCosts(Trait trait, CurrentRatingCosts ratingCosts) {
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