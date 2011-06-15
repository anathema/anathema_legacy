package net.sf.anathema.character.generic.framework.xml.trait.caste;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.IMinimumRestriction;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class CasteMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction
{
	private final List<ITraitType> affectedTraitTypes = new ArrayList<ITraitType>();
	private final String caste;
	private boolean isFreebie;
	IMinimumRestriction restriction;
	int strictMinimum = 0;
	
	public CasteMinimumRestriction(String caste, 
		       IMinimumRestriction restriction,
		       boolean isFreebie)
	{
		this(caste, 0, isFreebie);
		this.restriction = restriction;
		restriction.setIsFreebie(isFreebie);
	}
	
	public CasteMinimumRestriction(String caste, 
	       int minValue,
	       boolean isFreebie)
	{
		this.caste = caste;
		this.strictMinimum = minValue;
		this.isFreebie = isFreebie;
	}

	public void addTraitType(ITraitType traitType)
	{
		affectedTraitTypes.add(traitType);
	}

	public int getStrictMinimumValue()
	{
	    return restriction != null ? restriction.getStrictMinimumValue() : strictMinimum;
	}
	
	@Override
	public boolean isFullfilledWithout(ILimitationContext context,
			ITraitType traitType)
	{
		boolean caste = !context.getCasteType().toString().equals(this.caste);
		boolean fulfilled = !caste &&
			(restriction != null && restriction.isFullfilledWithout(context, traitType));
		return caste || fulfilled;
	}
	
	public int getCalculationMinValue(ILimitationContext context, ITraitType traitType)
	{
		if (!isFreebie)
			return 0;
		if (!context.getCasteType().toString().equals(this.caste))
			return 0;
		if (restriction != null)
			return restriction.getCalculationMinValue(context, traitType);
		return getStrictMinimumValue();
	}
	
	public void setIsFreebie(boolean value)
	{
		if (restriction != null)
			restriction.setIsFreebie(value);
	}
	
	public void clear()
	{
		if (restriction != null)
			restriction.clear();
	}

}
