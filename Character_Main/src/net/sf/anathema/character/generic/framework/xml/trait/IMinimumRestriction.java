package net.sf.anathema.character.generic.framework.xml.trait;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IMinimumRestriction {

  boolean isFullfilledWithout(ILimitationContext context, ITraitType traitType);

  void clear();

  int getStrictMinimumValue();

  int getCalculationMinValue(ILimitationContext context, ITraitType traitType);

  void addTraitType(ITraitType type);

  void setIsFreebie(boolean isFreebie);
}