package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;

public class AdditionRulesTraitValueChangeChecker implements IValueChangeChecker {

  private final TraitType traitType;
  private Hero hero;
  private final IAdditionalTraitRules additionalRules;

  public AdditionRulesTraitValueChangeChecker(TraitType traitType, Hero hero, IAdditionalTraitRules additionalRules) {
    this.traitType = traitType;
    this.hero = hero;
    this.additionalRules = additionalRules;
  }

  @Override
  public boolean isValidNewValue(int value) {
    return additionalRules.isAllowedTraitValue(new ValuedTraitType(traitType, value), TraitModelFetcher.fetch(hero));
  }
}