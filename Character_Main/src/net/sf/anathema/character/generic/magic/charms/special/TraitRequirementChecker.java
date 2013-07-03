package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.ValuedTraitType;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(ICharm charm, ValuedTraitType requirement);
}