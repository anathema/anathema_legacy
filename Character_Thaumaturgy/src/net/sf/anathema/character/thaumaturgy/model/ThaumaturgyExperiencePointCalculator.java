package net.sf.anathema.character.thaumaturgy.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;

public class ThaumaturgyExperiencePointCalculator implements IAdditionalModelExperienceCalculator
{
	IThaumaturgyModel model;
	
	public ThaumaturgyExperiencePointCalculator(IThaumaturgyModel model)
	{
		this.model = model;
	}

	@Override
	public int calculateCost() {
		int artCost = model.isFavored() ? 8 : 10;
		int totalCost = 0;
		
		for (IThaumaturgyMagic art : model.getLearnedDegrees())
			totalCost += artCost * (art.getCurrentValue() - art.getCreationValue());
		
		for (IThaumaturgyMagic procedure : model.getLearnedProcedures())
			totalCost += procedure.getCreationValue() == 0 ? 1 : 0;
		
		return totalCost;
	}

	@Override
	public int calculateGain() {
		return 0;
	}
	
}
