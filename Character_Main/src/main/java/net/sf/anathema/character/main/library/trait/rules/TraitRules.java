package net.sf.anathema.character.main.library.trait.rules;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.LowerableState;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.data.Range;

public class TraitRules implements ITraitRules {
  private int capModifier = 0;
  private final ITraitTemplate template;
  private final TraitType traitType;
  private Range modifiedCreationRange;
  private Hero hero;

  public TraitRules(TraitType traitType, ITraitTemplate template, Hero hero) {
    Preconditions.checkNotNull(traitType, "TemplateType must not be null.");
    Preconditions.checkNotNull(template, "Template must not be null.");
    this.traitType = traitType;
    this.hero = hero;
    this.template = template;
  }

  @Override
  public int getAbsoluteMaximumValue() {
    return template.getLimitation().getAbsoluteLimit(hero);
  }

  private int getCreationMaximumValue() {
    if (modifiedCreationRange == null) {
      return getCurrentMaximumValue(true);
    }
    return modifiedCreationRange.getUpperBound();
  }

  @Override
  public int getCurrentMaximumValue(boolean modified) {
    return template.getLimitation().getCurrentMaximum(hero, modified) + (modified ? capModifier : 0);
  }

  @Override
  public int getAbsoluteMinimumValue() {
    if (modifiedCreationRange == null) {
      return template.getMinimumValue(hero);
    }
    return modifiedCreationRange.getLowerBound();
  }

  @Override
  public int getCalculationMinValue() {
    return template.getCalculationMinValue(hero, traitType);
  }

  @Override
  public int getStartValue() {
    return template.getStartValue();
  }

  @Override
  public void setCapModifier(int modifier) {
    capModifier = modifier;
  }

  @Override
  public boolean isLowerable() {
    LowerableState lowerableState = template.getLowerableState();
    return lowerableState != LowerableState.Default && lowerableState != LowerableState.Immutable;
  }

  @Override
  public int getZeroCalculationCost() {
    return template.getZeroLevelValue();
  }

  protected final ITraitTemplate getTemplate() {
    return template;
  }

  @Override
  public TraitType getType() {
    return traitType;
  }

  @Override
  public void setModifiedCreationRange(Range range) {
    this.modifiedCreationRange = range;
  }

  @Override
  public int getExperiencedValue(int creationValue, int demandedValue) {
    Range range;
    int maximumValue = getCurrentMaximumValue(true);
    if (isLowerable()) {
      range = new Range(getAbsoluteMinimumValue(), maximumValue);
    } else {
      boolean isImmutable = template.getLowerableState() == LowerableState.Immutable;
      range = new Range(Math.max(Math.min(creationValue, maximumValue), getAbsoluteMinimumValue()), isImmutable ? creationValue : maximumValue);
    }
    int correctedValue = getCorrectedValue(demandedValue, range);
    if (isLowerable()) {
      return correctedValue;
    }
    return correctedValue;
    //the purpose for the below is unclear... hopefully it is safe to remove
    //return correctedValue <= creationValue ? ITraitRules.UNEXPERIENCED : correctedValue;
  }

  @Override
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

  @Override
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