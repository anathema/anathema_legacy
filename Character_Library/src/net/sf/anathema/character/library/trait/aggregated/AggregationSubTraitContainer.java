package net.sf.anathema.character.library.trait.aggregated;

import java.awt.Toolkit;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class AggregationSubTraitContainer extends AbstractSubTraitContainer {

  private final ITraitRules traitRules;
  private final ITrait parent;
  private final ITraitContext traitContext;
  private final IValueChangeChecker valueChangeChecker;

  public AggregationSubTraitContainer(
      ITraitRules traitRules,
      ITraitContext traitContext,
      IValueChangeChecker valueChangeChecker,
      ITrait parent,
      String... unremovableNames) {
    Ensure.ensureArgumentNotNull(traitRules);
    this.traitRules = traitRules;
    this.traitContext = traitContext;
    this.valueChangeChecker = valueChangeChecker;
    this.parent = parent;
    addUnremovableSubTraits(unremovableNames);
  }

  @Override
  protected ISubTrait createSubTrait(String name) {
    int startValue = getSubTraits().length == 0 ? traitRules.getStartValue() : 0;
    ITraitRules aggregatedTraitRules = traitRules.deriveAggregatedRules(name, startValue);
    return new AggregatedSubTrait(aggregatedTraitRules, traitContext, valueChangeChecker, parent, name);
  }

  @Override
  protected void handleAdditionOfContainedEquivalent(ISubTrait subTrait) {
    Toolkit.getDefaultToolkit().beep();
  }

  @Override
  protected boolean isNewSubTraitAllowed() {
    return true;
  }
}