package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface IModifiableGenericTrait extends IGenericTrait {

  void setCurrentValue(int value);
}