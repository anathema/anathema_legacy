package net.sf.anathema.character.library.trait.aggregated;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.AbstractFavorableTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.library.trait.rules.IFavorableTraitRules;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;

public class AggregatedTrait extends AbstractFavorableTrait implements IAggregatedTrait {

  private final ITraitFavorization traitFavorization;
  private final ISubTraitContainer subTraits;
  private final ICharacterChangeListener changeListener = new DedicatedCharacterChangeAdapter() {
    @Override
    public void casteChanged() {
      getFallbackTrait().resetCurrentValue();
      getFavorization().updateFavorableStateToCaste();
    }
  };

  public IDefaultTrait getFallbackTrait() {
    return getSubTraits().getSubTraits()[0];
  }

  public AggregatedTrait(
      IFavorableTraitRules traitRules,
      IBasicCharacterData basicData,
      ICharacterListening listening,
      ITraitContext traitContext,
      IValueChangeChecker valueChangeChecker,
      ICasteType caste,
      IIncrementChecker favoredIncrementChecker,
      String... unremovableSubTraits) {
    super(traitRules, traitContext);
    this.traitFavorization = new TraitFavorization(
        basicData,
        caste,
        favoredIncrementChecker,
        this,
        traitRules.isRequiredFavored());
    this.subTraits = new AggregationSubTraitContainer(
        traitRules,
        traitContext,
        valueChangeChecker,
        getType(),
        traitFavorization,
        unremovableSubTraits);
    subTraits.addSubTraitListener(new ISubTraitListener() {
      public void subTraitAdded(ISubTrait subTrait) {
        fireValueChangedEvent();
      }

      public void subTraitRemoved(ISubTrait subTrait) {
        fireValueChangedEvent();
      }

      public void subTraitValueChanged() {
        fireValueChangedEvent();
      }
    });
    listening.addChangeListener(changeListener);
    getFavorization().updateFavorableStateToCaste();
  }

  private void fireValueChangedEvent() {
    getCurrentValueControl().fireValueChangedEvent(getCurrentValue());
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

  @Override
  public String toString() {
    return getType().getId();
  }
}