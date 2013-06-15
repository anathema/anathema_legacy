package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface GenericTraitProvider {
  IGenericTrait getTrait(ITraitType type);
}
