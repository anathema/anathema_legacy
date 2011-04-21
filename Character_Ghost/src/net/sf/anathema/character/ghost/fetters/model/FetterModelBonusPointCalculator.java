package net.sf.anathema.character.ghost.fetters.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;

public class FetterModelBonusPointCalculator implements IAdditionalModelBonusPointCalculator
{
	final IGhostFettersModel model;
	
	public FetterModelBonusPointCalculator(IGhostFettersModel model)
	{
		this.model = model;
	}
	
	@Override
	public int getBonusPointCost()
	{
		return model.getBonusPointsSpent();
	}

	@Override
	public int getBonusPointsGranted() 
	{
		return 0;
	}

	@Override
	public void recalculate() 
	{
	}

}
