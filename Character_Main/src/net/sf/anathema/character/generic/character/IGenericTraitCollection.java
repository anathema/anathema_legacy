package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IGenericTraitCollection extends GenericTraitProvider {

  IGenericTrait[] getTraits(ITraitType[] traitTypes);

  IFavorableGenericTrait getFavorableTrait(ITraitType type);

  boolean isFavoredOrCasteTrait(ITraitType type);
}
