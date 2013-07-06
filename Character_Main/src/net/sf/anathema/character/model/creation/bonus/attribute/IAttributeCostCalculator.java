package net.sf.anathema.character.model.creation.bonus.attribute;

public interface IAttributeCostCalculator {

  void calculateCosts();

  int getFreePointsSpent(boolean favored);

  int getBonusPointsSpent();

  int getFavoredPicksSpent();

  void calculateAttributeCosts();

  int getBonusPoints();
}