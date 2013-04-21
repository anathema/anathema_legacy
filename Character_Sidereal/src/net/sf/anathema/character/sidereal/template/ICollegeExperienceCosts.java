package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;

public interface ICollegeExperienceCosts {

  CurrentRatingCosts getCollegeExperienceCost(boolean favored);
}