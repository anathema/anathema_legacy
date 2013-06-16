package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class LearnRangeContext implements GenericTraitProvider {
  private final GenericTraitProvider traitCollection;
  private final TraitRequirementChecker requirementChecker;
  private final ICharm charm;

  public LearnRangeContext(GenericTraitProvider traitCollection, TraitRequirementChecker requirementChecker,
                           ICharm charm) {
    this.traitCollection = traitCollection;
    this.requirementChecker = requirementChecker;
    this.charm = charm;
  }

  @Override
  public GenericTrait getTrait(ITraitType type) {
    return traitCollection.getTrait(type);
  }

  public boolean isMinimumSatisfied(GenericTrait requirement) {
    return requirementChecker.isMinimumSatisfied(charm, requirement);
  }
}