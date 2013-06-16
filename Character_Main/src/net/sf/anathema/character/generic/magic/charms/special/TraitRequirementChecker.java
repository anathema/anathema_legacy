package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.GenericTrait;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(ICharm charm, GenericTrait requirement);
}