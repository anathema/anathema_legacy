package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.model.TraitMap;

public class LearnRangeContext {
  private final TraitMap traitCollection;
  private final TraitRequirementChecker requirementChecker;
  private final Charm charm;

  public LearnRangeContext(TraitMap traitCollection, TraitRequirementChecker requirementChecker, Charm charm) {
    this.traitCollection = traitCollection;
    this.requirementChecker = requirementChecker;
    this.charm = charm;
  }

  public ValuedTraitType getTrait(TraitType type) {
    return traitCollection.getTrait(type);
  }

  public boolean isMinimumSatisfied(ValuedTraitType requirement) {
    return requirementChecker.isMinimumSatisfied(charm, requirement);
  }
}