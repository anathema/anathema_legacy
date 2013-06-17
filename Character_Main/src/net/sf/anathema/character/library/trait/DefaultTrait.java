package net.sf.anathema.character.library.trait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.library.trait.favorable.NullTraitFavorization;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.library.trait.rules.IFavorableTraitRules;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.data.Range;
import org.jmock.example.announcer.Announcer;

public class DefaultTrait implements Trait {

  private final Announcer<IChangeListener> rangeControl = Announcer.to(IChangeListener.class);
  private int capModifier = 0;
  private int creationValue;
  private int experiencedValue = ITraitRules.UNEXPERIENCED;
  private final IValueChangeChecker checker;
  private ITraitFavorization traitFavorization;
  private final ITraitRules traitRules;
  private final Announcer<IIntValueChangedListener> creationPointControl = Announcer.to(IIntValueChangedListener.class);
  private final Announcer<IIntValueChangedListener> currentValueControl = Announcer.to(IIntValueChangedListener.class);
  private final TraitContext traitContext;

  public DefaultTrait(IFavorableTraitRules traitRules, ICasteType[] castes, TraitContext traitContext, IBasicCharacterData basicData,
                      ICharacterListening listening, IValueChangeChecker valueChangeChecker, IncrementChecker favoredIncrementChecker) {
    this(traitRules, traitContext, valueChangeChecker);
    this.traitFavorization = new TraitFavorization(basicData, castes, favoredIncrementChecker, this, traitRules.isRequiredFavored());
    listening.addChangeListener(new ResetCurrentValueOnCasteChange());
    listening.addChangeListener(new UpdateFavoredStateOnCasteChange());
    traitFavorization.updateFavorableStateToCaste();
  }

  public DefaultTrait(ITraitRules traitRules, TraitContext traitContext, IValueChangeChecker checker) {
    Preconditions.checkNotNull(traitRules);
    this.traitRules = traitRules;
    this.traitContext = traitContext;
    this.traitFavorization = new NullTraitFavorization();
    this.checker = checker;
    this.creationValue = traitRules.getStartValue();
  }

  @Override
  public final int getCreationValue() {
    return creationValue;
  }

  @Override
  public ITraitFavorization getFavorization() {
    return traitFavorization;
  }

  @Override
  public void setCreationValue(int value) {
    if (traitFavorization.isFavored()) {
      value = Math.max(value, traitFavorization.getMinimalValue());
    }
    int correctedValue = traitRules.getCreationValue(value);
    if (this.creationValue == correctedValue) {
      return;
    }
    this.creationValue = correctedValue;
    creationPointControl.announce().valueChanged(this.creationValue);
    traitContext.getTraitValueStrategy().notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    if (this.creationValue == value) {
      return;
    }
    this.creationValue = value;
    creationPointControl.announce().valueChanged(this.creationValue);
    traitContext.getTraitValueStrategy().notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void resetCreationValue() {
    setCreationValue(getCreationValue());
  }

  @Override
  public final void resetExperiencedValue() {
    if (getExperiencedValue() != ITraitRules.UNEXPERIENCED) {
      setExperiencedValue(Math.max(getCreationValue(), getExperiencedValue()));
    }
  }

  @Override
  public final void addRangeListener(IChangeListener listener) {
    rangeControl.addListener(listener);
  }

  @Override
  public String toString() {
    return getType() + ":" + getCreationValue();
  }

  @Override
  public int getCurrentValue() {
    return traitContext.getTraitValueStrategy().getCurrentValue(this);
  }

  @Override
  public final int getCalculationValue() {
    return traitContext.getTraitValueStrategy().getCalculationValue(this);
  }

  @Override
  public int getCreationCalculationValue() {
    return Math.max(getCurrentValue(), getZeroCalculationValue());
  }

  @Override
  public void setCurrentValue(int value) {
    if (!checker.isValidNewValue(value)) {
      resetCurrentValue();
    } else {
      if (value == getCurrentValue()) {
        return;
      }
      traitContext.getTraitValueStrategy().setValue(this, value);
    }
  }

  @Override
  public final int getExperiencedValue() {
    return experiencedValue;
  }

  @Override
  public final void setExperiencedValue(int value) {
    int correctedValue = traitRules.getExperiencedValue(getCreationValue(), value);
    if (correctedValue == experiencedValue) {
      return;
    }
    this.experiencedValue = correctedValue;
    traitContext.getTraitValueStrategy().notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void setUncheckedExperiencedValue(int value) {
    if (value == experiencedValue) {
      return;
    }
    this.experiencedValue = value;
    traitContext.getTraitValueStrategy().notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void resetCurrentValue() {
    traitContext.getTraitValueStrategy().resetCurrentValue(this);
  }

  @Override
  public void applyCapModifier(int modifier) {
    capModifier += modifier;
    traitRules.setCapModifier(capModifier);
  }

  @Override
  public int getUnmodifiedMaximalValue() {
    return traitRules.getCurrentMaximumValue(false);
  }

  @Override
  public int getModifiedMaximalValue() {
    return traitRules.getCurrentMaximumValue(true);
  }

  @Override
  public void setModifiedCreationRange(int lowerBound, int upperBound) {
    traitRules.setModifiedCreationRange(new Range(lowerBound, upperBound));
    resetCreationValue();
  }

  @Override
  public final int getMinimalValue() {
    return traitContext.getTraitValueStrategy().getMinimalValue(this);
  }

  @Override
  public boolean isCasteOrFavored() {
    return getFavorization().isCasteOrFavored();
  }

  @Override
  public int getExperiencedCalculationValue() {
    return traitRules.getExperienceCalculationValue(creationValue, experiencedValue, getCurrentValue());
  }

  @Override
  public final int getCalculationMinValue() {
    return traitRules.getCalculationMinValue();
  }

  @Override
  public final boolean isLowerable() {
    return traitRules.isLowerable();
  }

  @Override
  public int getAbsoluteMinValue() {
    return traitRules.getAbsoluteMinimumValue();
  }

  @Override
  public final TraitType getType() {
    return traitRules.getType();
  }

  @Override
  public final int getMaximalValue() {
    return traitRules.getAbsoluteMaximumValue();
  }

  public final int getZeroCalculationValue() {
    return traitRules.getZeroCalculationCost();
  }

  @Override
  public int getInitialValue() {
    return traitRules.getStartValue();
  }

  @Override
  public final void addCreationPointListener(IIntValueChangedListener listener) {
    creationPointControl.addListener(listener);
  }

  @Override
  public final void removeCreationPointListener(IIntValueChangedListener listener) {
    creationPointControl.removeListener(listener);
  }

  @Override
  public final void addCurrentValueListener(IIntValueChangedListener listener) {
    currentValueControl.addListener(listener);
  }

  @Override
  public int hashCode() {
    return getType().getId().hashCode();
  }

  public class ResetCurrentValueOnCasteChange extends DedicatedCharacterChangeAdapter {
    @Override
    public void casteChanged() {
      resetCurrentValue();
    }
  }

  public class UpdateFavoredStateOnCasteChange extends DedicatedCharacterChangeAdapter {
    @Override
    public void casteChanged() {
      traitFavorization.updateFavorableStateToCaste();
    }
  }
}