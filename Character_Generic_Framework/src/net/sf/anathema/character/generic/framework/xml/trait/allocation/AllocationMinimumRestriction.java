package net.sf.anathema.character.generic.framework.xml.trait.allocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.IMinimumRestriction;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class AllocationMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction
{
  private final Map<ILimitationContext,Map<ITraitType, Integer>> claimMap = new HashMap<ILimitationContext,Map<ITraitType, Integer>>();
  private final List<AllocationMinimumRestriction> siblings;
  private final int dotCount;
  private int strictMinimumValue = 0;
  private final List<ITraitType> alternateTraitTypes = new ArrayList<ITraitType>();
  private ILimitationContext latestContext = null;
  private ITraitType latestTrait = null;

  public AllocationMinimumRestriction(int dotCount, List<AllocationMinimumRestriction> siblings) {
    this.dotCount = dotCount;
    this.siblings = siblings;
  }

  public boolean isFullfilledWithout(ILimitationContext context, ITraitType traitType) {
    int remainingDots = dotCount;
    latestContext = context;
    latestTrait = traitType;
    for (ITraitType type : alternateTraitTypes)
      if (type != traitType)
      {
    	  //DefaultTrait trait = context.getTraitCollection().getTrait(type);
    	  int currentDots = context.getTraitCollection().getTrait(type).getCurrentValue();
    	  int externalDots = getExternalClaims(context, type);
    	  int claimedDots = Math.max(currentDots - externalDots, 0);
    	  claimedDots = Math.min(claimedDots, remainingDots);
    	  claimDots(context, type, claimedDots);
    	  remainingDots -= claimedDots;
      }
    strictMinimumValue = remainingDots;
    return remainingDots == 0;
  }
  
  private void claimDots(ILimitationContext context, ITraitType type, int dots)
  {
	  Map<ITraitType, Integer> map = claimMap.get(context);
	  if (map == null)
	  {
		  map = new HashMap<ITraitType, Integer>();
		  claimMap.put(context, map);
	  }
	  map.put(type, dots);
  }

  private int getExternalClaims(ILimitationContext context, ITraitType traitType)
  {
	  int claimed = 0;
	  for (AllocationMinimumRestriction sibling : siblings)
	  {
		  if (sibling == this)
			  continue;
		  try
		  {
			  Map<ITraitType, Integer> map = sibling.claimMap.get(context);
			  claimed += map.get(traitType);
		  }
		  catch (NullPointerException e) { }
	  }
	  return claimed;
  }

  public void clear()
  {
	  claimMap.clear();
  }

  public void addTraitType(ITraitType traitType) {
    alternateTraitTypes.add(traitType);
  }

  public int getStrictMinimumValue()
  {
	  claimDots(latestContext, latestTrait, strictMinimumValue);
	  return strictMinimumValue + getExternalClaims(latestContext, latestTrait);
  }
}