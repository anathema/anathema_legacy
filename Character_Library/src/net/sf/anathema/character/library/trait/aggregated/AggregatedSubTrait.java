package net.sf.anathema.character.library.trait.aggregated;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class AggregatedSubTrait extends DefaultTrait implements ISubTrait {

  private final ITrait parent;
  private final String name;

  public AggregatedSubTrait(
      ITraitRules traitRules,
      ITraitValueStrategy traitValueStrategy,
      IValueChangeChecker checker,
      ITrait parent,
      String name) {
    super(traitRules, traitValueStrategy, checker);
    this.parent = parent;
    this.name = name;
  }

  public ITrait getBasicTrait() {
    return parent;
  }

  public String getName() {
    return name;
  }
}