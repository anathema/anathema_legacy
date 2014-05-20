package net.sf.anathema.character.main.library.trait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.library.ITraitFavorization;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.library.trait.favorable.NullTraitFavorization;
import net.sf.anathema.character.main.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.main.library.trait.rules.IFavorableTraitRules;
import net.sf.anathema.character.main.library.trait.rules.ITraitRules;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.TraitValueStrategy;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.ConceptChange;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.data.Range;
import org.jmock.example.announcer.Announcer;

public class DefaultTrait implements Trait {

  private int capModifier = 0;
  private int creationValue;
  private int experiencedValue = ITraitRules.UNEXPERIENCED;
  private final ValueChangeChecker checker;
  private ITraitFavorization traitFavorization;
  private final ITraitRules traitRules;
  private final Announcer<IntValueChangedListener> creationPointControl = Announcer.to(IntValueChangedListener.class);
  private final Announcer<IntValueChangedListener> currentValueControl = Announcer.to(IntValueChangedListener.class);
  private final TraitValueStrategy valueStrategy;

  public DefaultTrait(Hero hero, IFavorableTraitRules traitRules, CasteType[] castes, ValueChangeChecker valueChangeChecker,
                      IncrementChecker favoredIncrementChecker) {
    this(hero, traitRules, valueChangeChecker);
    this.traitFavorization = new TraitFavorization(hero, castes, favoredIncrementChecker, this, traitRules.isRequiredFavored());
    hero.getChangeAnnouncer().addListener(new ResetCurrentValueOnCasteChange());
    hero.getChangeAnnouncer().addListener(new UpdateFavoredStateOnCasteChange());
    traitFavorization.updateFavorableStateToCaste();
  }

  public DefaultTrait(Hero hero, ITraitRules traitRules, ValueChangeChecker checker) {
    Preconditions.checkNotNull(traitRules);
    this.traitRules = traitRules;
    TraitModel traits = TraitModelFetcher.fetch(hero);
    this.valueStrategy = traits.getValueStrategy();
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
    valueStrategy.notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    if (this.creationValue == value) {
      return;
    }
    this.creationValue = value;
    creationPointControl.announce().valueChanged(this.creationValue);
    valueStrategy.notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
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
  public String toString() {
    return getType() + ":" + getCreationValue();
  }

  @Override
  public int getCurrentValue() {
    return valueStrategy.getCurrentValue(this);
  }

  @Override
  public final int getCalculationValue() {
    return valueStrategy.getCalculationValue(this);
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
      valueStrategy.setValue(this, value);
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
    valueStrategy.notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void setUncheckedExperiencedValue(int value) {
    if (value == experiencedValue) {
      return;
    }
    this.experiencedValue = value;
    valueStrategy.notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void resetCurrentValue() {
    valueStrategy.resetCurrentValue(this);
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
    return valueStrategy.getMinimalValue(this);
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
  public final void addCreationPointListener(IntValueChangedListener listener) {
    creationPointControl.addListener(listener);
  }

  @Override
  public final void removeCreationPointListener(IntValueChangedListener listener) {
    creationPointControl.removeListener(listener);
  }

  @Override
  public final void addCurrentValueListener(IntValueChangedListener listener) {
    currentValueControl.addListener(listener);
  }

  @Override
  public int hashCode() {
    return getType().getId().hashCode();
  }

  public class ResetCurrentValueOnCasteChange implements FlavoredChangeListener {

    @Override
    public void changeOccurred(ChangeFlavor flavor) {
      if (flavor == ConceptChange.FLAVOR_CASTE) {
        resetCurrentValue();
      }
    }
  }

  public class UpdateFavoredStateOnCasteChange implements FlavoredChangeListener {

    @Override
    public void changeOccurred(ChangeFlavor flavor) {
      if (flavor == ConceptChange.FLAVOR_CASTE) {
        traitFavorization.updateFavorableStateToCaste();
      }
    }
  }
}