package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.rules.ITraitRules;


public abstract class AbstractFavorableTrait implements IFavorableTrait {

  protected final ITraitRules traitRules;

  public AbstractFavorableTrait(ITraitRules traitRules) {
    this.traitRules = traitRules;
  }

  public boolean isCasteOrFavored() {
    return getFavorization().isCasteOrFavored();
  }

  public boolean isCreationLearned() {
    return getCreationValue() > 0;
  }

  public final ITraitType getType() {
    return traitRules.getType();
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
    return traitRules.getAbsoluteMinimumValue();
  }

  public final int getZeroCalculationValue() {
    return traitRules.getZeroCalculationCost();
  }

  public int getInitialValue() {
    return traitRules.getStartValue();
  }
}