package net.sf.anathema.character.main.magic.charm.special;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(Charm charm, ValuedTraitType requirement);
}