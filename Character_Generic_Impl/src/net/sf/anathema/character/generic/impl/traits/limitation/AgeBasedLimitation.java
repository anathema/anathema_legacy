package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;

public class AgeBasedLimitation implements ITraitLimitation
{
	private final int absoluteLimit;
	private final int[] ageArray = { 100, 200, 300, 400, 500 };
	
	public AgeBasedLimitation(int maxValue)
	{
		this.absoluteLimit = maxValue;
	}
	
	@Override
	public int getAbsoluteLimit(ILimitationContext limitationContext)
	{
		return absoluteLimit;
	}

	@Override
	public int getCurrentMaximum(ILimitationContext limitationContext, boolean modified)
	{
		int age = limitationContext.getAge();
		int categories = 0;
		for (; categories != ageArray.length; categories++)
			if (age < ageArray[categories])
				break;
		return 5 + categories;
	}
	
	@Override
	public AgeBasedLimitation clone()
	{
	  return this;
	}
}
