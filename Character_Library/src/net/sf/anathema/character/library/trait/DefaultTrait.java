package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.specialty.ISpecialtiesContainer;
import net.sf.anathema.character.library.trait.specialty.SpecialtiesContainer;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;
import net.sf.anathema.lib.data.Range;

public class DefaultTrait implements ITrait {

  private final IntValueControl creationPointControl = new IntValueControl();
  private final IntValueControl currentValueControl = new IntValueControl();
  private final ChangeControl rangeControl = new ChangeControl();
  private int creationValue;
  private int experiencedValue = ITraitRules.UNEXPERIENCED;
  private final IValueChangeChecker checker;
  private final ITraitValueStrategy traitValueStrategy;
  private final ITraitRules traitRules;

  public DefaultTrait(ITraitRules traitRules, ITraitValueStrategy traitValueStrategy, IValueChangeChecker checker) {
    this.checker = checker;
    this.traitRules = traitRules;
    this.traitValueStrategy = traitValueStrategy;
    this.creationValue = traitRules.getStartValue();
  }

  public ISpecialtiesContainer createSpecialtiesContainer() {
    return new SpecialtiesContainer(this, traitRules, traitValueStrategy);
  }

  public final ITraitType getType() {
    return traitRules.getType();
  }

  public final int getCreationValue() {
    return creationValue;
  }

  public void setCreationValue(int value) {
    int correctedValue = traitRules.getCreationValue(value);
    if (this.creationValue == correctedValue) {
      return;
    }
    this.creationValue = correctedValue;
    creationPointControl.fireValueChangedEvent(this.creationValue);
    traitValueStrategy.notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
  }

  public final void resetCreationValue() {
    setCreationValue(getCreationValue());
  }

  public final void resetExperiencedValue() {
    setExperiencedValue(Math.max(getCreationValue(), getExperiencedValue()));
  }

  public final void addCreationPointListener(IIntValueChangedListener listener) {
    creationPointControl.addIntValueChangeListener(listener);
  }

  public final void removeCreationPointListener(IIntValueChangedListener listener) {
    creationPointControl.removeIntValueChangeListener(listener);
  }

  public final void addCurrentValueListener(IIntValueChangedListener listener) {
    currentValueControl.addIntValueChangeListener(listener);
  }

  public final void addRangeListener(IChangeListener listener) {
    rangeControl.addChangeListener(listener);
  }

  public final void removeCurrentValueListener(IIntValueChangedListener listener) {
    currentValueControl.removeIntValueChangeListener(listener);
  }

  public final int getMinimalValue() {
    return traitValueStrategy.getMinimalValue(this);
  }

  public final int getMaximalValue() {
    return traitRules.getAbsoluteMaximumValue();
  }

  public final boolean isLowerable() {
    return traitRules.isLowerable();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DefaultTrait)) {
      return false;
    }
    DefaultTrait other = (DefaultTrait) obj;
    return other.getCreationValue() == getCreationValue()
        && other.getType() == getType()
        && other.getExperiencedValue() == getExperiencedValue();
  }

  @Override
  public int hashCode() {
    return 13 * getCreationValue();
  }

  @Override
  public String toString() {
    return getType() + ":" + getCreationValue(); //$NON-NLS-1$
  }

  public int getCurrentValue() {
    return traitValueStrategy.getCurrentValue(this);
  }

  public final int getCalculationValue() {
    return traitValueStrategy.getCalculationValue(this);
  }

  public int getCreationCalculationValue() {
    return Math.max(getCurrentValue(), getZeroCalculationValue());
  }

  public int getExperiencedCalculationValue() {
    return traitRules.getExperienceCalculationValue(creationValue, experiencedValue, getCurrentValue());
  }

  public int getAbsoluteMinValue() {
    return traitRules.getAbsoluteMinimumValue();
  }

  public final int getZeroCalculationValue() {
    return traitRules.getZeroCalculationCost();
  }

  public void setCurrentValue(int value) {
    if (!checker.isValidNewValue(value)) {
      resetCurrentValue();
    }
    else {
      if (value == getCurrentValue()) {
        return;
      }
      traitValueStrategy.setValue(this, value);
    }
  }

  public final int getExperiencedValue() {
    return experiencedValue;
  }

  public final void setExperiencedValue(int value) {
    int correctedValue = traitRules.getExperiencedValue(getCreationValue(), value);
    if (correctedValue == experiencedValue) {
      return;
    }
    this.experiencedValue = correctedValue;
    traitValueStrategy.notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  public final void resetCurrentValue() {
    traitValueStrategy.resetCurrentValue(this);
  }

  public int getInitialValue() {
    return traitRules.getStartValue();
  }

  public boolean isCreationLearned() {
    return getCreationValue() > 0;
  }

  public void setModifiedCreationRange(int lowerBound, int upperBound) {
    traitRules.setModifiedCreationRange(new Range(lowerBound, upperBound));
    resetCreationValue();
  }
}