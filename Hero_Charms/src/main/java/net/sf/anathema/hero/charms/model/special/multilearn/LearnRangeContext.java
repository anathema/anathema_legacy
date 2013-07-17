package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.character.main.GenericTraitProvider;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.hero.traits.TraitMap;

public class LearnRangeContext implements GenericTraitProvider {
  private final TraitMap traitCollection;
  private final TraitRequirementChecker requirementChecker;
  private final Charm charm;

  public LearnRangeContext(TraitMap traitCollection, TraitRequirementChecker requirementChecker, Charm charm) {
    this.traitCollection = traitCollection;
    this.requirementChecker = requirementChecker;
    this.charm = charm;
  }

  @Override
  public ValuedTraitType getTrait(TraitType type) {
    return traitCollection.getTrait(type);
  }

  public boolean isMinimumSatisfied(ValuedTraitType requirement) {
    return requirementChecker.isMinimumSatisfied(charm, requirement);
  }
}