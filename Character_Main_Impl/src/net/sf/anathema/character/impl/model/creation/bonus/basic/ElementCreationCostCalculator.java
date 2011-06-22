package net.sf.anathema.character.impl.model.creation.bonus.basic;

public class ElementCreationCostCalculator {

	public ElementCreationCost calculateElementCreationCost(ICostElement element, int freeDots, int bonusPointFactor)
	{
		return calculateElementCreationCost(element, freeDots, 0, 0, bonusPointFactor);
	}
	
  public ElementCreationCost calculateElementCreationCost(ICostElement element, int freeDots, int extraFavoredDots, int extraGenericDots, int bonusPointFactor) {
    int bonusPointsSpent = 0;
    int extraFavoredDotsSpent = 0;
    int extraGenericDotsSpent = 0;
    int pointsToAdd = element.getCalculationValue() - element.getZeroCalculationValue();
    int dotsSpent = Math.min(freeDots, pointsToAdd);
    pointsToAdd -= dotsSpent;
    if (pointsToAdd > 0)
    {
    	extraFavoredDotsSpent = Math.min(extraFavoredDots, pointsToAdd);
    	pointsToAdd -= extraFavoredDotsSpent;
    }
    if (pointsToAdd > 0)
    {
    	extraGenericDotsSpent = Math.min(extraGenericDots, pointsToAdd);
    	pointsToAdd -= extraGenericDotsSpent;
    }
    if (pointsToAdd > 0) {
      bonusPointsSpent += pointsToAdd * bonusPointFactor;
    }
    return new ElementCreationCost(dotsSpent, extraFavoredDotsSpent, extraGenericDotsSpent, bonusPointsSpent);
  }
}