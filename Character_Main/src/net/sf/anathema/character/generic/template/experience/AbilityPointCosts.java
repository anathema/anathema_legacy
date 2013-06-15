package net.sf.anathema.character.generic.template.experience;

public interface AbilityPointCosts {

  CurrentRatingCosts getAbilityCosts(boolean favored);

  int getDefaultSpecialtyDotsPerPoint();

  int getFavoredSpecialtyDotsPerPoint();
  
  int getMaximumFreeAbilityRank();
}