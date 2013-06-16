package net.sf.anathema.character.generic.framework.xml.trait;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.TraitType;

public interface IMinimumRestriction {

  boolean isFullfilledWithout(ILimitationContext context, TraitType traitType);

  void clear();

  int getStrictMinimumValue();

  int getCalculationMinValue(ILimitationContext context, TraitType traitType);

  void addTraitType(TraitType type);

  void setIsFreebie(boolean isFreebie);
}