package net.sf.anathema.character.library.trait.specialty;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.library.trait.ITrait;

public interface ISpecialty extends ITrait, INamedGenericTrait {

  public ITrait getBasicTrait();
}