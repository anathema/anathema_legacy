package net.sf.anathema.character.generic.framework.xml.trait.caste;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.IMinimumRestriction;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.util.ArrayList;
import java.util.List;

public class CasteMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction
{
	private final List<ITraitType> affectedTraitTypes = new ArrayList<>();
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

	@Override
    public void addTraitType(ITraitType traitType)
	{
		affectedTraitTypes.add(traitType);
	}

	@Override
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
	
	@Override
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
	
	@Override
    public void setIsFreebie(boolean value)
	{
		if (restriction != null)
			restriction.setIsFreebie(value);
	}
	
	@Override
    public void clear()
	{
		if (restriction != null)
			restriction.clear();
	}

}
