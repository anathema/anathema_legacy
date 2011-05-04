package net.sf.anathema.character.library.trait.rules;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.library.trait.aggregated.AggregatedTraitTemplate;
import net.sf.anathema.lib.data.Range;

public class TraitRules implements ITraitRules
{
  private int capModifier = 0;
  private final ITraitTemplate template;
  private final ILimitationContext limitationContext;
  private final ITraitType traitType;
  private Range modifiedCreationRange;

  public TraitRules(ITraitType traitType, ITraitTemplate template, ILimitationContext limitationContext) {
    Ensure.ensureArgumentNotNull("TemplateType must not be null.", traitType); //$NON-NLS-1$
    Ensure.ensureArgumentNotNull("Template must not be null.", template); //$NON-NLS-1$
    this.traitType = traitType;
    this.template = template;
    this.limitationContext = limitationContext;
  }

  public int getAbsoluteMaximumValue() {
    return template.getLimitation().getAbsoluteLimit(limitationContext);
  }

  private int getCreationMaximumValue() {
    if (modifiedCreationRange == null) {
      return getCurrentMaximumValue(true);
    }
    return modifiedCreationRange.getUpperBound();
  }

  public int getCurrentMaximumValue(boolean modified) {
    return template.getLimitation().getCurrentMaximum(limitationContext, modified) +
    	(modified ? capModifier : 0);
  }

  public int getAbsoluteMinimumValue() {
    if (modifiedCreationRange == null) {
      return template.getMinimumValue(limitationContext);
    }
    return modifiedCreationRange.getLowerBound();
  }

  public int getStartValue() {
    return template.getStartValue();
  }
  
  public void setCapModifier(int modifier)
  {
	  capModifier = modifier;
  }

  public ITraitRules deriveAggregatedRules(String subname, int startValue) {
    return derive(traitType, new AggregatedTraitTemplate(template, traitType, subname, startValue));
  }

  public ITraitRules derive(ITraitType type, ITraitTemplate deriveTemplate) {
    return new TraitRules(type, deriveTemplate, limitationContext);
  }

  public boolean isLowerable() {
    LowerableState lowerableState = template.getLowerableState();
    return lowerableState != LowerableState.Default && lowerableState != LowerableState.Immutable;
  }

  public int getZeroCalculationCost() {
    return template.getZeroLevelValue();
  }

  protected final ITraitTemplate getTemplate() {
    return template;
  }

  public Range getModifiedRange(Range unmodifiedRange) {
    return unmodifiedRange;
  }

  public ITraitType getType() {
    return traitType;
  }

  public void setModifiedCreationRange(Range range) {
    this.modifiedCreationRange = range;
  }

  public int getExperiencedValue(int creationValue, int demandedValue) {
    Range range;
    int maximumValue = getCurrentMaximumValue(true);
    if (isLowerable()) {
      range = new Range(getAbsoluteMinimumValue(), maximumValue);
    }
    else {
      boolean isImmutable = template.getLowerableState() == LowerableState.Immutable;
      range = new Range(creationValue, isImmutable ? creationValue : maximumValue);
    }
    int correctedValue = getCorrectedValue(demandedValue, range);
    if (isLowerable()) {
      return correctedValue;
    }
    return correctedValue <= creationValue ? ITraitRules.UNEXPERIENCED : correctedValue;
  }

  public int getCreationValue(int demandedValue) {
    Range currentCreationPointRange = new Range(getAbsoluteMinimumValue(), getCreationMaximumValue());
    return getCorrectedValue(demandedValue, currentCreationPointRange);
  }

  private int getCorrectedValue(int value, Range allowedRange) {
    if (allowedRange.contains(value)) {
      return value;
    }
    if (value < allowedRange.getLowerBound()) {
      return allowedRange.getLowerBound();
    }
    return allowedRange.getUpperBound();
  }

  public int getExperienceCalculationValue(int creationValue, int experiencedValue, int currentValue) {
    if (template.getLowerableState() == LowerableState.LowerableRegain) {
      if (experiencedValue == UNEXPERIENCED) {
        return creationValue;
      }
      return currentValue;
    }
    return Math.max(currentValue, creationValue);
  }
}