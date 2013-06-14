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
    setTraitFavorization(new TraitFavorization(basicData, castes, favoredIncrementChecker, this, traitRules.isRequiredFavored()));
    listening.addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void casteChanged() {
        resetCurrentValue();
        getFavorization().updateFavorableStateToCaste();
      }
    });
    getFavorization().updateFavorableStateToCaste();
  }

  public DefaultTrait(ITraitRules traitRules, ITraitContext traitContext, IValueChangeChecker checker) {
    Preconditions.checkNotNull(traitRules);
    this.traitRules = traitRules;
    this.traitContext = traitContext;
    setTraitFavorization(new NullTraitFavorization());
    this.checker = checker;
    this.creationValue = traitRules.getStartValue();
  }

  @Override
  public void applyCapModifier(int modifier) {
    capModifier += modifier;
    getTraitRules().setCapModifier(capModifier);
  }

  @Override
  public int getUnmodifiedMaximalValue() {
    return getTraitRules().getCurrentMaximumValue(false);
  }

  @Override
  public int getModifiedMaximalValue() {
    return getTraitRules().getCurrentMaximumValue(true);
  }

  protected void setTraitFavorization(ITraitFavorization favorization) {
    this.traitFavorization = favorization;
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
    if (getFavorization().isFavored()) {
      value = Math.max(value, getFavorization().getMinimalValue());
    }
    int correctedValue = getTraitRules().getCreationValue(value);
    if (this.creationValue == correctedValue) {
      return;
    }
    this.creationValue = correctedValue;
    getCreationPointControl().announce().valueChanged(this.creationValue);
    getTraitValueStrategy().notifyOnCreationValueChange(getCurrentValue(), getCurrentValueControl());
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    if (this.creationValue == value) {
      return;
    }
    this.creationValue = value;
    getCreationPointControl().announce().valueChanged(this.creationValue);
    getTraitValueStrategy().notifyOnCreationValueChange(getCurrentValue(), getCurrentValueControl());
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
    return getTraitRules().getExperienceCalculationValue(creationValue, experiencedValue, getCurrentValue());
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
    int correctedValue = getTraitRules().getExperiencedValue(getCreationValue(), value);
    if (correctedValue == experiencedValue) {
      return;
    }
    this.experiencedValue = correctedValue;
    getTraitValueStrategy().notifyOnLearnedValueChange(getCurrentValue(), getCurrentValueControl());
  }

  @Override
  public final void setUncheckedExperiencedValue(int value) {
    if (value == experiencedValue) {
      return;
    }
    this.experiencedValue = value;
    getTraitValueStrategy().notifyOnLearnedValueChange(getCurrentValue(), getCurrentValueControl());
  }

  @Override
  public final void resetCurrentValue() {
    getTraitValueStrategy().resetCurrentValue(this);
  }

  @Override
  public void setModifiedCreationRange(int lowerBound, int upperBound) {
    getTraitRules().setModifiedCreationRange(new Range(lowerBound, upperBound));
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
    return getTraitRules().getCalculationMinValue();
  }

  @Override
  public final boolean isLowerable() {
    return getTraitRules().isLowerable();
  }

  @Override
  public int getAbsoluteMinValue() {
    return getTraitRules().getAbsoluteMinimumValue();
  }

  @Override
  public boolean isCasteOrFavored() {
    return getFavorization().isCasteOrFavored();
  }

  @Override
  public final ITraitType getType() {
    return getTraitRules().getType();
  }

  @Override
  public final int getMaximalValue() {
    return getTraitRules().getAbsoluteMaximumValue();
  }

  @Override
  public int hashCode() {
    return getType().getId().hashCode();
  }

  public final int getZeroCalculationValue() {
    return getTraitRules().getZeroCalculationCost();
  }

  @Override
  public int getInitialValue() {
    return getTraitRules().getStartValue();
  }

  @Override
  public final void addCreationPointListener(IIntValueChangedListener listener) {
    getCreationPointControl().addListener(listener);
  }

  @Override
  public final void removeCreationPointListener(IIntValueChangedListener listener) {
    getCreationPointControl().removeListener(listener);
  }

  @Override
  public final void addCurrentValueListener(IIntValueChangedListener listener) {
    getCurrentValueControl().addListener(listener);
  }

  @Override
  public final void removeCurrentValueListener(IIntValueChangedListener listener) {
    getCurrentValueControl().removeListener(listener);
  }

  private ITraitRules getTraitRules() {
    return traitRules;
  }

  private ITraitValueStrategy getTraitValueStrategy() {
    return traitContext.getTraitValueStrategy();
  }

  private Announcer<IIntValueChangedListener> getCreationPointControl() {
    return creationPointControl;
  }

  private Announcer<IIntValueChangedListener> getCurrentValueControl() {
    return currentValueControl;
  }

}