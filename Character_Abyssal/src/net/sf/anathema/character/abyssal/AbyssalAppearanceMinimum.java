package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.IExtendedMinimum;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class AbyssalAppearanceMinimum extends ReflectionEqualsObject implements IExtendedMinimum
{
	int currentMinimum = 1;
	
	@Override
	public void addTraitType(ITraitType type)
	{
		// nothing to do
	}

	@Override
	public void clear()
	{
		// nothing to do
	}

	@Override
	public int getStrictMinimumValue() {
		return currentMinimum;
	}

	@Override
	public boolean isFullfilledWithout(ILimitationContext context,
			ITraitType traitType)
	{
		int currentEssence = context.getTraitCollection()
			.getTrait(OtherTraitType.Essence).getCurrentValue();
		int currentAppearance = context.getTraitCollection().
				getTrait(AttributeType.Appearance).getCurrentValue();
		if (currentEssence == 4)
		{
			if (currentAppearance <= 2)
				currentMinimum = 1;
			else
				currentMinimum = 3;
			return false;
		}
		else if (currentEssence >= 5)
		{
			if (currentAppearance < 2)
				currentMinimum = 0;
			else
				currentMinimum = 4;
			return false;
		}
		currentMinimum = 1;
	
		return false;
	}
	
	public int getCalculationMinValue(ILimitationContext context, ITraitType traitType)
	{
		return getStrictMinimumValue();
	}
	
	public void setIsFreebie(boolean value)
	{
	}

	@Override
	public int getMinimum()
	{
		return 1;
	}

}
