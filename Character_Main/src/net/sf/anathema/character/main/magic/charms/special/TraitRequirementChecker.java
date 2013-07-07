package net.sf.anathema.character.main.magic.charms.special;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(ICharm charm, ValuedTraitType requirement);
}