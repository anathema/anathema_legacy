package net.sf.anathema.character.generic.framework.xml.trait.allocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.IMinimumRestriction;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class AllocationMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction
{
  private final Map<ILimitationContext,Map<ITraitType, Integer>> allocationMap = new HashMap<ILimitationContext,Map<ITraitType, Integer>>();
  private final List<AllocationMinimumRestriction> siblings;
  private final int dotCount;
  private int strictMinimumValue = 0;
  private final List<ITraitType> alternateTraitTypes = new ArrayList<ITraitType>();

  public AllocationMinimumRestriction(int dotCount, List<AllocationMinimumRestriction> siblings) {
    this.dotCount = dotCount;
    this.siblings = siblings;
  }

  public boolean isFullfilledWithout(ILimitationContext context, ITraitType traitType) {
    int remainingDots = dotCount;
    for (ITraitType type : alternateTraitTypes)
      if (type != traitType)
      {
    	  int currentDots = context.getTraitCollection().getTrait(type).getCurrentValue();
    	  int externalDots = getExternalAllocation(context, type);
    	  int allocatedDots = Math.max(currentDots - externalDots, 0);
    	  allocatedDots = Math.min(allocatedDots, remainingDots);
    	  allocateDots(context, type, allocatedDots);
    	  remainingDots -= allocatedDots;
      }
    strictMinimumValue = remainingDots;
    return remainingDots == 0;
  }
  
  private void allocateDots(ILimitationContext context, ITraitType type, int dots)
  {
	  Map<ITraitType, Integer> map = allocationMap.get(context);
	  if (map == null)
	  {
		  map = new HashMap<ITraitType, Integer>();
		  allocationMap.put(context, map);
	  }
	  map.put(type, dots);
  }

  private int getExternalAllocation(ILimitationContext context, ITraitType traitType)
  {
	  int allocated = 0;

	  for (AllocationMinimumRestriction sibling : siblings)
	  {
		  if (sibling == this)
			  continue;
		  try
		  {
			  Map<ITraitType, Integer> map = sibling.allocationMap.get(context);
			  
			  allocated += map.get(traitType);
		  }
		  catch (NullPointerException e)
		  {

		  }
	  }
	  return allocated;
  }

  public void clear()
  {
	  allocationMap.clear();
  }

  public void addTraitType(ITraitType traitType) {
    alternateTraitTypes.add(traitType);
  }

  public int getStrictMinimumValue() {
    return strictMinimumValue;
  }
}