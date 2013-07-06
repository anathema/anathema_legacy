package net.sf.anathema.character.model.creation.bonus.attribute;

public interface IAttributeCostCalculator {

  int getFreePointsSpent(boolean favored);

  int getBonusPointsSpent();

  void calculateAttributeCosts();

  int getBonusPoints();
}