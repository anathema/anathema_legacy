package net.sf.anathema.character.model.creation.bonus.ability;

public interface IAbilityCostCalculator  {

  void recalculate();

  int getFreePointsSpent(boolean favored);

  int getBonusPointsSpent();

  int getFavoredPicksSpent();
}