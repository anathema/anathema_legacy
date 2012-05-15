package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;

public interface ICollegeExperienceCosts {

  ICurrentRatingCosts getCollegeExperienceCost(boolean favored);
}