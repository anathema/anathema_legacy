package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface TraitRequirementChecker {
  boolean isMinimumSatisfied(ICharm charm, IGenericTrait requirement);
}