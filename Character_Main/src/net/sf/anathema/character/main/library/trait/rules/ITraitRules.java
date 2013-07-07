package net.sf.anathema.character.main.library.trait.rules;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.data.Range;

public interface ITraitRules {

  int UNEXPERIENCED = -1;

  int getAbsoluteMaximumValue();

  int getAbsoluteMinimumValue();

  int getCurrentMaximumValue(boolean modified);

  int getCalculationMinValue();

  int getStartValue();

  int getZeroCalculationCost();

  boolean isLowerable();

  TraitType getType();

  int getExperiencedValue(int creationValue, int demandedValue);

  int getCreationValue(int demandedValue);

  int getExperienceCalculationValue(int creationValue, int experiencedValue, int currentValue);

  void setCapModifier(int modifier);

  void setModifiedCreationRange(Range range);
}