package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.trait.IValueChangeChecker;

public class AdditionRulesTraitValueChangeChecker implements IValueChangeChecker {

  private final ITraitType traitType;
  private final IAdditionalTraitRules additionalRules;
  private final ILimitationContext context;

  public AdditionRulesTraitValueChangeChecker(
      ITraitType traitType,
      ILimitationContext context,
      IAdditionalTraitRules additionalRules) {
    this.traitType = traitType;
    this.context = context;
    this.additionalRules = additionalRules;
  }

  public boolean isValidNewValue(int value) {
    return additionalRules.isAllowedTraitValue(new ValuedTraitType(traitType, value), context.getTraitCollection());
  }
}