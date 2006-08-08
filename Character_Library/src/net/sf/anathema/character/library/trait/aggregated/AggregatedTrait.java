package net.sf.anathema.character.library.trait.aggregated;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.AbstractFavorableTrait;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.favorable.NullFavorization;
import net.sf.anathema.character.library.trait.rules.ITraitRules;

public class AggregatedTrait extends AbstractFavorableTrait {

  private final List<IModifiableTrait> subTraits = new ArrayList<IModifiableTrait>();
  private final ITraitFavorization traitFavorization;

  public AggregatedTrait(ITraitRules traitRules, ITraitValueStrategy traitValueStrategy) {
    super(traitRules, traitValueStrategy);
    // TODO Favorization umstellen
    this.traitFavorization = new NullFavorization();
  }

  public ITraitFavorization getFavorization() {
    return traitFavorization;
  }

  public int getCalculationValue() {
    throw new UnsupportedOperationException("Use subtraits instead.");
  }

  public int getCreationCalculationValue() {
    throw new UnsupportedOperationException("Use subtraits instead.");
  }

  public int getCreationValue() {
    throw new UnsupportedOperationException("Use subtraits instead.");
  }

  public int getExperiencedCalculationValue() {
    throw new UnsupportedOperationException("Use subtraits instead.");
  }

  public int getExperiencedValue() {
    throw new UnsupportedOperationException("Use subtraits instead.");
  }

  public int getCurrentValue() {
    int currentValue = 0;
    for (IGenericTrait trait : subTraits) {
      currentValue = Math.max(currentValue, trait.getCurrentValue());
    }
    return currentValue;
  }
}