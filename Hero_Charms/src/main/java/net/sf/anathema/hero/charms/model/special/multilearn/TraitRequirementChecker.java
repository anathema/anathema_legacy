package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(Charm charm, ValuedTraitType requirement);
}