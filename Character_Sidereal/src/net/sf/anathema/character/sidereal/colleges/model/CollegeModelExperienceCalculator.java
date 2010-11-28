package net.sf.anathema.character.sidereal.colleges.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.template.ICollegeExperienceCosts;

public class CollegeModelExperienceCalculator implements IAdditionalModelExperienceCalculator {

  private final IAstrologicalHouse[] allHouses;
  private final ICurrentRatingCosts collegeExperienceCost;

  public CollegeModelExperienceCalculator(IAstrologicalHouse[] allHouses, ICollegeExperienceCosts experienceCosts) {
    this.allHouses = allHouses;
    this.collegeExperienceCost = experienceCosts.getCollegeExperienceCost();
  }

  public int calculateCost() {
    int totalCost = 0;
    for (IAstrologicalHouse house : allHouses) {
      for (IFavorableDefaultTrait college : house.getColleges()) {
        totalCost += TraitRatingCostCalculator.getTraitRatingCosts(college, collegeExperienceCost);
      }
    }
    return totalCost;
  }

  public int calculateGain() {
    return 0;
  }
}