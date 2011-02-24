package net.sf.anathema.character.generic.framework.xml.trait.caste;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.IMinimumRestriction;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class CasteMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction
{
	private final List<ITraitType> affectedTraitTypes = new ArrayList<ITraitType>();
	private final String caste;
	IMinimumRestriction restriction;
	int strictMinimum = 0;
	
	public CasteMinimumRestriction(String caste, 
		       IMinimumRestriction restriction)
	{
		this(caste, 0);
		this.restriction = restriction;
	}
	
	public CasteMinimumRestriction(String caste, 
	       int minValue)
	{
		this.caste = caste;
		this.strictMinimum = minValue;
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
		boolean fulfilled = (restriction != null && restriction.isFullfilledWithout(context, traitType));
		return caste || fulfilled;
	}

}
