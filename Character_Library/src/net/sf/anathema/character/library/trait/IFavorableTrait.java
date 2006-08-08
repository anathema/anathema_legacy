package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.library.ITraitFavorization;

public interface IFavorableTrait extends ITrait, IFavorableGenericTrait {

  public ITraitFavorization getFavorization();
}