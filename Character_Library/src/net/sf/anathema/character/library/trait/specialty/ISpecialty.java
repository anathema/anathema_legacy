package net.sf.anathema.character.library.trait.specialty;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.library.trait.IModifiableTrait;

public interface ISpecialty extends IModifiableTrait, INamedGenericTrait {

  public IModifiableTrait getBasicTrait();
}