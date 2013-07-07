package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(ICharm charm, ValuedTraitType requirement);
}