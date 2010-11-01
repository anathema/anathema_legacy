package net.sf.anathema.character.generic.template.experience;

public interface IAbilityPointCosts {

  public ICurrentRatingCosts getAbilityCosts(boolean favored);

  public int getDefaultSpecialtyDotsPerPoint();

  public int getFavoredSpecialtyDotsPerPoint();
  
  public int getMaximumFreeAbilityRank();
}