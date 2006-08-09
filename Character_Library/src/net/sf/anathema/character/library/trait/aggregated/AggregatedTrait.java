package net.sf.anathema.character.library.trait.aggregated;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.AbstractFavorableTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.favorable.NullFavorization;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;

public class AggregatedTrait extends AbstractFavorableTrait implements IAggregatedTrait {

  private final ITraitFavorization traitFavorization;
  private final ISubTraitContainer subTraits;

  public AggregatedTrait(
      ITraitRules traitRules,
      ITraitValueStrategy traitValueStrategy,
      IValueChangeChecker valueChangeChecker,
      String... unremovableSubTraits) {
    super(traitRules, traitValueStrategy);
    subTraits = new AggregationSubTraitContainer(
        traitRules.derive(),
        traitValueStrategy,
        valueChangeChecker,
        this,
        unremovableSubTraits);
    // TODO Favorization umstellen
    this.traitFavorization = new NullFavorization();
  }

  public ITraitFavorization getFavorization() {
    return traitFavorization;
  }

  // TODO Punkteberechnung umstellen
  public int getCalculationValue() {
    return 0;
  }

  // TODO Punkteberechnung umstellen
  public int getCreationCalculationValue() {
    return 0;
  }

  // TODO Punkteberechnung umstellen
  public int getCreationValue() {
    return 0;
  }

  // TODO Punkteberechnung umstellen
  public int getExperiencedCalculationValue() {
    return 0;
  }

  // TODO Punkteberechnung umstellen
  public int getExperiencedValue() {
    return 0;
  }

  public int getCurrentValue() {
    int currentValue = 0;
    for (IGenericTrait trait : subTraits.getSubTraits()) {
      currentValue = Math.max(currentValue, trait.getCurrentValue());
    }
    return currentValue;
  }

  public ISubTraitContainer getSubTraits() {
    return subTraits;
  }

  public void accept(ITraitVisitor visitor) {
    visitor.visitAggregatedTrait(this);
  }
}