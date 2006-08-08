package net.sf.anathema.character.library.trait.subtrait;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.ITrait;

public interface ISubTrait extends IModifiableTrait, INamedGenericTrait {

  public ITrait getBasicTrait();
}