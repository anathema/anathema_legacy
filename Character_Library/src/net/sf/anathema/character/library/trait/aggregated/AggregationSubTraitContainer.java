package net.sf.anathema.character.library.trait.aggregated;

import java.awt.Toolkit;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.favorable.AggregatedTraitFavorization;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class AggregationSubTraitContainer extends AbstractSubTraitContainer {

  private final ITraitRules traitRules;
  private final ITraitContext traitContext;
  private final IValueChangeChecker valueChangeChecker;
  private final ITraitType type;
  private final ITraitFavorization traitFavorization;

  public AggregationSubTraitContainer(
      ITraitRules traitRules,
      ITraitContext traitContext,
      IValueChangeChecker valueChangeChecker,
      ITraitType basicType,
      ITraitFavorization traitFavorization,
      String... unremovableNames) {
    Ensure.ensureArgumentNotNull(traitRules);
    this.traitRules = traitRules;
    this.traitContext = traitContext;
    this.valueChangeChecker = valueChangeChecker;
    this.type = basicType;
    this.traitFavorization = traitFavorization;
    addUnremovableSubTraits(unremovableNames);
  }

  @Override
  protected ISubTrait createSubTrait(String name) {
    int startValue = getSubTraits().length == 0 ? traitRules.getStartValue() : 0;
    ITraitRules aggregatedTraitRules = traitRules.deriveAggregatedRules(name, startValue);
    AggregatedSubTrait subTrait = new AggregatedSubTrait(
        aggregatedTraitRules,
        traitContext,
        valueChangeChecker,
        type,
        name);
    subTrait.setTraitFavorization(new AggregatedTraitFavorization(traitFavorization, subTrait, this));
    return subTrait;
  }

  @Override
  protected void handleAdditionOfContainedEquivalent(ISubTrait subTrait) {
    Toolkit.getDefaultToolkit().beep();
  }

  @Override
  public void removeSubTrait(ISubTrait subtrait) {
    super.removeSubTrait(subtrait);
    if (traitFavorization.isFavored()) {
      traitFavorization.ensureMinimalValue();
    }
  }

  @Override
  protected boolean isNewSubTraitAllowed() {
    return true;
  }
}