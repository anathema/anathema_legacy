package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.model.traits.TraitMap;

public class LearnRangeContext implements GenericTraitProvider {
  private final TraitMap traitCollection;
  private final TraitRequirementChecker requirementChecker;
  private final ICharm charm;

  public LearnRangeContext(TraitMap traitCollection, TraitRequirementChecker requirementChecker, ICharm charm) {
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