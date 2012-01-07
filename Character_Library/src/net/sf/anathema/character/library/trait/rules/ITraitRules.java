package net.sf.anathema.character.library.trait.rules;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.data.Range;

public interface ITraitRules {

  int UNEXPERIENCED = -1;

  ITraitRules deriveAggregatedRules(String subname, int startValue);

  ITraitRules derive(ITraitType traitType, ITraitTemplate template);

  int getAbsoluteMaximumValue();

  int getAbsoluteMinimumValue();
  
  int getCurrentMaximumValue(boolean modified);
  
  int getCalculationMinValue();
  
  void setCapModifier(int modifier);

  Range getModifiedRange(Range unmodifiedRange);

  int getStartValue();

  int getZeroCalculationCost();

  boolean isLowerable();

  ITraitType getType();

  void setModifiedCreationRange(Range range);

  int getExperiencedValue(int creationValue, int demandedValue);

  int getCreationValue(int demandedValue);

  int getExperienceCalculationValue(int creationValue, int experiencedValue, int currentValue);
}