package net.sf.anathema.character.library.trait.aggregated;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.AbstractFavorableTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.library.trait.rules.IFavorableTraitRules;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;

public class AggregatedTrait extends AbstractFavorableTrait implements IAggregatedTrait {

  private final ITraitFavorization traitFavorization;
  private final ISubTraitContainer subTraits;
  private ICharacterChangeListener changeListener = new DedicatedCharacterChangeAdapter() {
    @Override
    public void casteChanged() {
      getFavorization().updateFavorableStateToCaste();
    }
  };

  public AggregatedTrait(
      IFavorableTraitRules traitRules,
      IBasicCharacterData basicData,
      ICharacterListening listening,
      ITraitValueStrategy traitValueStrategy,
      IValueChangeChecker valueChangeChecker,
      ICasteType< ? extends ICasteTypeVisitor> caste,
      IIncrementChecker favoredIncrementChecker,
      String... unremovableSubTraits) {
    super(traitRules, traitValueStrategy);
    subTraits = new AggregationSubTraitContainer(
        traitRules.derive(),
        traitValueStrategy,
        valueChangeChecker,
        this,
        unremovableSubTraits);
    this.traitFavorization = new TraitFavorization(
        basicData,
        caste,
        favoredIncrementChecker,
        this,
        traitRules.isRequiredFavored());
    listening.addChangeListener(changeListener);
    getFavorization().updateFavorableStateToCaste();
  }

  public ITraitFavorization getFavorization() {
    return traitFavorization;
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