package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface GenericTraitProvider {

  GenericTrait getTrait(ITraitType type);
}
