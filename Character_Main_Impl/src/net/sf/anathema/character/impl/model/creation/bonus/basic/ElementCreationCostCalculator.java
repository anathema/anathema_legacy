package net.sf.anathema.character.impl.model.creation.bonus.basic;

public class ElementCreationCostCalculator {

	public ElementCreationCost calculateElementCreationCost(ICostElement element, int freeDots, int bonusPointFactor)
	{
		return calculateElementCreationCost(element, freeDots, 0, bonusPointFactor);
	}
	
  public ElementCreationCost calculateElementCreationCost(ICostElement element, int freeDots, int extraDots, int bonusPointFactor) {
    int bonusPointsSpent = 0;
    int extraDotsSpent = 0;
    int pointsToAdd = element.getCalculationValue() - element.getZeroCalculationValue();
    int dotsSpent = Math.min(freeDots, pointsToAdd);
    pointsToAdd -= dotsSpent;
    if (pointsToAdd > 0)
    {
    	extraDotsSpent = Math.min(extraDots, pointsToAdd);
    	pointsToAdd -= extraDotsSpent;
    }
    if (pointsToAdd > 0) {
      bonusPointsSpent += pointsToAdd * bonusPointFactor;
    }
    return new ElementCreationCost(dotsSpent, extraDotsSpent, bonusPointsSpent);
  }
}