package net.sf.anathema.character.library.trait.rules;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.data.Range;

public interface ITraitRules {

  public static final int UNEXPERIENCED = -1;

  public ITraitRules deriveAggregatedRules(String subname, int startValue);

  public ITraitRules derive(ITraitType traitType, ITraitTemplate template);

  public int getAbsoluteMaximumValue();

  public int getAbsoluteMinimumValue();
  
  public int getCurrentMaximumValue(boolean modified);
  
  public int getCalculationMinValue();
  
  public void setCapModifier(int modifier);

  public Range getModifiedRange(Range unmodifiedRange);

  public int getStartValue();

  public int getZeroCalculationCost();

  public boolean isLowerable();

  public ITraitType getType();

  public void setModifiedCreationRange(Range range);

  public int getExperiencedValue(int creationValue, int demandedValue);

  public int getCreationValue(int demandedValue);

  public int getExperienceCalculationValue(int creationValue, int experiencedValue, int currentValue);
}