package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.IValueChangeChecker;

public abstract class AbstractTraitFactory {

  private final ITraitContext traitContext;
  private final IAdditionalTraitRules additionalRules;

  public AbstractTraitFactory(ITraitContext traitContext, IAdditionalTraitRules additionalRules) {
    this.traitContext = traitContext;
    this.additionalRules = additionalRules;
  }

  protected final IValueChangeChecker createValueIncrementChecker(ITraitType traitType) {
    //TODO: traitcontext.getLimitationContext().getTraitCollection is null when this method is called.
    //Statistics.getTraitConfiguration is not yet initialized.
    return new AdditionRulesTraitValueChangeChecker(traitType, traitContext.getLimitationContext(), additionalRules);
  }
}