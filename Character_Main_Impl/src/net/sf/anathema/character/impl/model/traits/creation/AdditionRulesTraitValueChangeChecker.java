package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.trait.IValueChangeChecker;

public class AdditionRulesTraitValueChangeChecker implements IValueChangeChecker {

  private final ITraitType traitType;
  private final ILimitationContext limitationContext;
  private final IAdditionalTraitRules additionalRules;

  public AdditionRulesTraitValueChangeChecker(
      ITraitType traitType,
      ILimitationContext limitationContext,
      IAdditionalTraitRules additionalRules) {
    this.traitType = traitType;
    this.limitationContext = limitationContext;
    this.additionalRules = additionalRules;
  }

  public boolean isValidNewValue(int value) {
    return additionalRules.isAllowedTraitValue(new ValuedTraitType(traitType, value), limitationContext);
  }
}