package net.sf.anathema.character.generic.traits;

import net.sf.anathema.character.generic.character.ILimitationContext;

public interface ITraitMinimum {

  public int getMinimumValue(ILimitationContext context);
  
  public int getCalculationMinValue(ILimitationContext context, ITraitType traitType);
}