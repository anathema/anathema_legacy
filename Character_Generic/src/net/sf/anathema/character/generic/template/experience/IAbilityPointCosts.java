package net.sf.anathema.character.generic.template.experience;

public interface IAbilityPointCosts {

  ICurrentRatingCosts getAbilityCosts(boolean favored);

  int getDefaultSpecialtyDotsPerPoint();

  int getFavoredSpecialtyDotsPerPoint();
  
  int getMaximumFreeAbilityRank();
}