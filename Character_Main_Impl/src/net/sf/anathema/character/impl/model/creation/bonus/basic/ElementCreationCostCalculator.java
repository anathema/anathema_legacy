package net.sf.anathema.character.impl.model.creation.bonus.basic;

public class ElementCreationCostCalculator {

  public ElementCreationCost calculateElementCreationCost(ICostElement element, int freeDots, int bonusPointFactor) {
    int bonusPointsSpent = 0;
    int pointsToAdd = element.getCalculationValue() - element.getZeroCalculationValue();
    int dotsSpent = Math.min(freeDots, pointsToAdd);
    pointsToAdd -= dotsSpent;
    if (pointsToAdd > 0) {
      bonusPointsSpent += pointsToAdd * bonusPointFactor;
    }
    return new ElementCreationCost(dotsSpent, bonusPointsSpent);
  }
}