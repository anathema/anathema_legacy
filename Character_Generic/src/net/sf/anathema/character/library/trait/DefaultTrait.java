package net.sf.anathema.character.library.trait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.NullTraitFavorization;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.library.trait.rules.IFavorableTraitRules;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.data.Range;
import org.jmock.example.announcer.Announcer;

public class DefaultTrait implements IFavorableDefaultTrait {

  private final Announcer<IChangeListener> rangeControl = Announcer.to(IChangeListener.class);
  private int capModifier = 0;
  private int creationValue;
  private int experiencedValue = ITraitRules.UNEXPERIENCED;
  private final IValueChangeChecker checker;
  private ITraitFavorization traitFavorization;
  private final ITraitRules traitRules;
  private final Announcer<IIntValueChangedListener> creationPointControl = Announcer.to(IIntValueChangedListener.class);
  private final Announcer<IIntValueChangedListener> currentValueControl = Announcer.to(IIntValueChangedListener.class);
  private final ITraitContext traitContext;

  public DefaultTrait(IFavorableTraitRules traitRules, ICasteType[] castes, ITraitContext traitContext, IBasicCharacterData basicData,
                      ICharacterListening listening, IValueChangeChecker valueChangeChecker, IIncrementChecker favoredIncrementChecker) {
    this(traitRules, traitContext, valueChangeChecker);
    this.traitFavorization = new TraitFavorization(basicData, castes, favoredIncrementChecker, this, traitRules.isRequiredFavored());
    listening.addChangeListener(new ResetCurrentValueOnCasteChange());
    listening.addChangeListener(new UpdateFavoredStateOnCasteChange());
    traitFavorization.updateFavorableStateToCaste();
  }

  public DefaultTrait(ITraitRules traitRules, ITraitContext traitContext, IValueChangeChecker checker) {
    Preconditions.checkNotNull(traitRules);
    this.traitRules = traitRules;
    this.traitContext = traitContext;
    this.traitFavorization = new NullTraitFavorization();
    this.checker = checker;
    this.creationValue = traitRules.getStartValue();
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
    getTraitValueStrategy().notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    if (this.creationValue == value) {
      return;
    }
    this.creationValue = value;
    creationPointControl.announce().valueChanged(this.creationValue);
    getTraitValueStrategy().notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
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
    return getTraitValueStrategy().getCurrentValue(this);
  }

  @Override
  public final int getCalculationValue() {
    return getTraitValueStrategy().getCalculationValue(this);
  }

  @Override
  public int getCreationCalculationValue() {
    return Math.max(getCurrentValue(), getZeroCalculationValue());
  }

  @Override
  public int getExperiencedCalculationValue() {
    return traitRules.getExperienceCalculationValue(creationValue, experiencedValue, getCurrentValue());
  }

  @Override
  public void setCurrentValue(int value) {
    if (!checker.isValidNewValue(value)) {
      resetCurrentValue();
    } else {
      if (value == getCurrentValue()) {
        return;
      }
      getTraitValueStrategy().setValue(this, value);
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
    getTraitValueStrategy().notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void setUncheckedExperiencedValue(int value) {
    if (value == experiencedValue) {
      return;
    }
    this.experiencedValue = value;
    getTraitValueStrategy().notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void resetCurrentValue() {
    getTraitValueStrategy().resetCurrentValue(this);
  }

  @Override
  public void setModifiedCreationRange(int lowerBound, int upperBound) {
    traitRules.setModifiedCreationRange(new Range(lowerBound, upperBound));
    resetCreationValue();
  }

  @Override
  public void accept(ITraitVisitor visitor) {
    visitor.visitDefaultTrait(this);
  }

  @Override
  public final int getMinimalValue() {
    return getTraitValueStrategy().getMinimalValue(this);
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
  public boolean isCasteOrFavored() {
    return getFavorization().isCasteOrFavored();
  }

  @Override
  public final ITraitType getType() {
    return traitRules.getType();
  }

  @Override
  public final int getMaximalValue() {
    return traitRules.getAbsoluteMaximumValue();
  }

  @Override
  public int hashCode() {
    return getType().getId().hashCode();
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
  public final void removeCurrentValueListener(IIntValueChangedListener listener) {
    currentValueControl.removeListener(listener);
  }

  private ITraitValueStrategy getTraitValueStrategy() {
    return traitContext.getTraitValueStrategy();
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