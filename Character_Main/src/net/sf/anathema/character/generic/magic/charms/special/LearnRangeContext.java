package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.GenericTrait;
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
  public GenericTrait getTrait(TraitType type) {
    return traitCollection.getTrait(type);
  }

  public boolean isMinimumSatisfied(GenericTrait requirement) {
    return requirementChecker.isMinimumSatisfied(charm, requirement);
  }
}