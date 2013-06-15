package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;

public interface ITraitCollection extends IGenericTraitCollection {

  @Override
  ITrait getTrait(ITraitType type);

  IFavorableTrait getFavorableTrait(ITraitType type);

  @Override
  ITrait[] getTraits(ITraitType[] traitTypes);

  IFavorableTrait[] getFavorableTraits(ITraitType[] traitTypes);
}