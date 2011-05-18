package net.sf.anathema.character.thaumaturgy.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;

public class ThaumaturgyBonusPointCalculator implements IAdditionalModelBonusPointCalculator
{
	IThaumaturgyModel model;
	int totalCost;
	
	public ThaumaturgyBonusPointCalculator(IThaumaturgyModel model)
	{
		this.model = model;
	}
	
	@Override
	public int getBonusPointCost()
	{
		return totalCost;
	}

	@Override
	public int getBonusPointsGranted() {
		return 0;
	}

	@Override
	public void recalculate() {
		int artCost = model.isFavored() ? 4 : 5;
		int totalProcedures = model.getLearnedProcedures().size();
		totalCost = 0;
		
		for (IThaumaturgyMagic art : model.getLearnedDegrees())
			totalCost += artCost * art.getCurrentValue();
		
		totalCost += (totalProcedures / 3) +
			(totalProcedures % 3 != 0 ? 1 : 0);
	}
	
}
