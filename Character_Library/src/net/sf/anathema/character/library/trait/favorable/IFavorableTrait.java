package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.ITrait;

public interface IFavorableTrait extends ITrait, IFavorableGenericTrait {

  public ITraitFavorization getFavorization();
}