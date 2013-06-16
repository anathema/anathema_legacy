package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;

public interface GenericTraitProvider {

  GenericTrait getTrait(TraitType type);
}
