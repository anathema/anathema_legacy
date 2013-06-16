package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IGenericTraitCollection extends GenericTraitProvider {

  GenericTrait[] getTraits(ITraitType[] traitTypes);

  boolean isFavoredOrCasteTrait(ITraitType type);
}
