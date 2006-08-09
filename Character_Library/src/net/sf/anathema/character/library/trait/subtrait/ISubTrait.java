package net.sf.anathema.character.library.trait.subtrait;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.character.library.trait.ITrait;

public interface ISubTrait extends IDefaultTrait, INamedGenericTrait {

  public ITrait getBasicTrait();
}