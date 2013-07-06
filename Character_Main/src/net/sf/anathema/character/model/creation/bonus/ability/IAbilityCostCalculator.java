package net.sf.anathema.character.model.creation.bonus.ability;

public interface IAbilityCostCalculator  {

  void calculateCosts();

  int getFreePointsSpent(boolean favored);

  int getBonusPointsSpent();

  int getFavoredPicksSpent();

  int getSpecialtyBonusPointCosts();

  int getFreeSpecialtyPointsSpent();
}