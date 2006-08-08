package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.NullFavorization;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.library.trait.rules.IFavorableTraitRules;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.specialty.ISpecialtiesContainer;
import net.sf.anathema.character.library.trait.specialty.SpecialtiesContainer;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;
import net.sf.anathema.lib.data.Range;

public class DefaultTrait extends AbstractFavorableTrait implements IFavorableModifiableTrait {

  private final IntValueControl creationPointControl = new IntValueControl();
  private final IntValueControl currentValueControl = new IntValueControl();
  private final ChangeControl rangeControl = new ChangeControl();
  private int creationValue;
  private int experiencedValue = ITraitRules.UNEXPERIENCED;
  private final IValueChangeChecker checker;
  private final ITraitValueStrategy traitValueStrategy;
  private ITraitFavorization traitFavorization;
  private ICharacterChangeListener changeListener = new DedicatedCharacterChangeAdapter() {
    @Override
    public void casteChanged() {
      resetCurrentValue();
      getFavorization().updateFavorableStateToCaste();
    }
  };

  public DefaultTrait(
      IFavorableTraitRules traitRules,
      ICasteType< ? extends ICasteTypeVisitor> caste,
      ITraitValueStrategy valueStrategy,
      IBasicCharacterData basicData,
      ICharacterListening listening,
      IValueChangeChecker valueChangeChecker,
      IIncrementChecker favoredIncrementChecker) {
    this(traitRules, valueStrategy, valueChangeChecker);
    traitFavorization = new TraitFavorization(
        basicData,
        caste,
        favoredIncrementChecker,
        this,
        traitRules.isRequiredFavored());
    listening.addChangeListener(changeListener);
    getFavorization().updateFavorableStateToCaste();
  }

  public DefaultTrait(ITraitRules traitRules, ITraitValueStrategy traitValueStrategy, IValueChangeChecker checker) {
    super(traitRules);
    this.traitFavorization = new NullFavorization();
    this.checker = checker;
    this.traitValueStrategy = traitValueStrategy;
    this.creationValue = traitRules.getStartValue();
  }

  public ISpecialtiesContainer createSpecialtiesContainer() {
    return new SpecialtiesContainer(this, traitRules, traitValueStrategy);
  }

  public final int getCreationValue() {
    return creationValue;
  }

  public ITraitFavorization getFavorization() {
    return traitFavorization;
  }

  public final void setCreationValue(int value) {
    if (getFavorization().getFavorableState() == FavorableState.Favored) {
      value = Math.max(value, 1);
    }
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

  public void setModifiedCreationRange(int lowerBound, int upperBound) {
    traitRules.setModifiedCreationRange(new Range(lowerBound, upperBound));
    resetCreationValue();
  }
}