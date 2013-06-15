package net.sf.anathema.character.generic.traits;

import net.sf.anathema.character.generic.character.ILimitationContext;

public interface ITraitMinimum {

  int getMinimumValue(ILimitationContext context);
  
  int getCalculationMinValue(ILimitationContext context, ITraitType traitType);
}