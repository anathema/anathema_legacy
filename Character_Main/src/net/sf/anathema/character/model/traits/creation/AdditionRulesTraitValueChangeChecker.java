package net.sf.anathema.character.model.traits.creation;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.trait.ValueChangeChecker;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class AdditionRulesTraitValueChangeChecker implements ValueChangeChecker {

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