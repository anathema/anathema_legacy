package net.sf.anathema.character.library.trait.aggregated;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class AggregatedSubTrait extends DefaultTrait implements ISubTrait {

  private final ITraitType basicType;
  private final String name;

  public AggregatedSubTrait(
      ITraitRules traitRules,
      ITraitContext traitContext,
      IValueChangeChecker checker,
      ITraitType type,
      ITraitFavorization traitFavorization,
      String name) {
    super(traitRules, traitContext, checker, traitFavorization);
    this.basicType = type;
    this.name = name;
  }

  public ITraitType getBasicTraitType() {
    return basicType;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getType() + " (" + getName() + "):" + getCreationValue(); //$NON-NLS-1$ //$NON-NLS-2$
  }
}