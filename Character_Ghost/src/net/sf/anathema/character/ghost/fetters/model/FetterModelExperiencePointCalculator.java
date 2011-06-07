package net.sf.anathema.character.ghost.fetters.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;

public class FetterModelExperiencePointCalculator implements IAdditionalModelExperienceCalculator
{
	final IGhostFettersModel model;
	
	public FetterModelExperiencePointCalculator(IGhostFettersModel model)
	{
		this.model = model;
	}
	
	@Override
	public int calculateCost()
	{
		return model.getXPSpent(); 
	}

	@Override
	public int calculateGain()
	{
		return 0;
	}

}
