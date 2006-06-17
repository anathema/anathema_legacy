package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IGenericTraitCollection {

  public IGenericTrait getTrait(ITraitType type);

  public IFavorableGenericTrait getFavorableTrait(ITraitType type);

  public boolean isFavoredOrCasteTrait(ITraitType type);
}