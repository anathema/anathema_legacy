package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(Charm charm, ValuedTraitType requirement);
}