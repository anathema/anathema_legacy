package net.sf.anathema.character.sidereal.colleges.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.template.ICollegeExperienceCosts;

public class CollegeModelExperienceCalculator implements IAdditionalModelExperienceCalculator {

  private final IAstrologicalHouse[] allHouses;
  private final ICollegeExperienceCosts costs;

  public CollegeModelExperienceCalculator(IAstrologicalHouse[] allHouses, ICollegeExperienceCosts experienceCosts) {
    this.allHouses = allHouses;
    this.costs = experienceCosts;
  }

  public int calculateCost() {
    int totalCost = 0;
    for (IAstrologicalHouse house : allHouses) {
      for (IFavorableDefaultTrait college : house.getColleges()) {
    	boolean favored = college.isCasteOrFavored();
        totalCost += TraitRatingCostCalculator.getTraitRatingCosts(college, costs.getCollegeExperienceCost(favored));
      }
    }
    return totalCost;
  }

  public int calculateGain() {
    return 0;
  }
}