package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public abstract class AbstractFavorableTrait implements IFavorableTrait {

  private final ITraitRules traitRules;
  private final IntValueControl creationPointControl = new IntValueControl();
  private final IntValueControl currentValueControl = new IntValueControl();
  protected final ITraitValueStrategy traitValueStrategy;

  public AbstractFavorableTrait(ITraitRules traitRules, ITraitValueStrategy traitValueStrategy) {
    this.traitRules = traitRules;
    this.traitValueStrategy = traitValueStrategy;
  }

  public final ISubTraitContainer createSpecialtiesContainer() {
    return new SpecialtiesContainer(this, getTraitRules(), traitValueStrategy);
  }

  public boolean isCasteOrFavored() {
    return getFavorization().isCasteOrFavored();
  }

  public boolean isCreationLearned() {
    return getCreationValue() > 0;
  }

  public final ITraitType getType() {
    return getTraitRules().getType();
  }

  public final int getMaximalValue() {
    return getTraitRules().getAbsoluteMaximumValue();
  }

  public final boolean isLowerable() {
    return getTraitRules().isLowerable();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DefaultTrait)) {
      return false;
    }
    AbstractFavorableTrait other = (AbstractFavorableTrait) obj;
    return other.getCreationValue() == getCreationValue()
        && other.getType() == getType()
        && other.getExperiencedValue() == getExperiencedValue();
  }

  @Override
  public int hashCode() {
    return getType().getId().hashCode();
  }

  public int getAbsoluteMinValue() {
    return getTraitRules().getAbsoluteMinimumValue();
  }

  public final int getZeroCalculationValue() {
    return getTraitRules().getZeroCalculationCost();
  }

  public int getInitialValue() {
    return getTraitRules().getStartValue();
  }

  public final void addCreationPointListener(IIntValueChangedListener listener) {
    getCreationPointControl().addIntValueChangeListener(listener);
  }

  public final void removeCreationPointListener(IIntValueChangedListener listener) {
    getCreationPointControl().removeIntValueChangeListener(listener);
  }

  public final void addCurrentValueListener(IIntValueChangedListener listener) {
    getCurrentValueControl().addIntValueChangeListener(listener);
  }

  public final void removeCurrentValueListener(IIntValueChangedListener listener) {
    getCurrentValueControl().removeIntValueChangeListener(listener);
  }

  protected ITraitRules getTraitRules() {
    return traitRules;
  }

  protected IntValueControl getCreationPointControl() {
    return creationPointControl;
  }

  protected IntValueControl getCurrentValueControl() {
    return currentValueControl;
  }
}