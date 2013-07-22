package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(Charm charm, ValuedTraitType requirement);
}