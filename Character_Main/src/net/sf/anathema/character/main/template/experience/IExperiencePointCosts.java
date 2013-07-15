package net.sf.anathema.character.main.template.experience;

public interface IExperiencePointCosts {

  CurrentRatingCosts getAbilityCosts(boolean favored);

  CurrentRatingCosts getAttributeCosts(boolean favored);

  CurrentRatingCosts getEssenceCosts();

  int getSpecialtyCosts(boolean favored);

  CurrentRatingCosts getVirtueCosts();

  CurrentRatingCosts getWillpowerCosts();
}