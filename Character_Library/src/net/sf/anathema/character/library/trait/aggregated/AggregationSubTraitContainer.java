package net.sf.anathema.character.library.trait.aggregated;

import java.awt.Toolkit;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class AggregationSubTraitContainer extends AbstractSubTraitContainer {

  private final ITraitRules traitRules;
  private final ITrait parent;
  private final ITraitValueStrategy traitValueStrategy;
  private final IValueChangeChecker valueChangeChecker;

  public AggregationSubTraitContainer(
      ITraitRules traitRules,
      ITraitValueStrategy traitValueStrategy,
      IValueChangeChecker valueChangeChecker,
      ITrait parent,
      String... unremovableNames) {
    Ensure.ensureArgumentNotNull(traitRules);
    this.traitRules = traitRules;
    this.traitValueStrategy = traitValueStrategy;
    this.valueChangeChecker = valueChangeChecker;
    this.parent = parent;
    addUnremovableSubTraits(unremovableNames);
  }

  @Override
  protected ISubTrait createSubTrait(String name) {
    return new AggregatedSubTrait(traitRules, traitValueStrategy, valueChangeChecker, parent, name);
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