package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.trait.IValueChangeChecker;

public class AdditionRulesTraitValueChangeChecker implements IValueChangeChecker {

  private final ITraitType traitType;
  private final IAdditionalTraitRules additionalRules;
  private final IGenericTraitCollection collection;

  public AdditionRulesTraitValueChangeChecker(
      ITraitType traitType,
      IGenericTraitCollection collection,
      IAdditionalTraitRules additionalRules) {
    this.traitType = traitType;
    this.collection = collection;
    this.additionalRules = additionalRules;
  }

  public boolean isValidNewValue(int value) {
    return additionalRules.isAllowedTraitValue(new ValuedTraitType(traitType, value), collection);
  }
}