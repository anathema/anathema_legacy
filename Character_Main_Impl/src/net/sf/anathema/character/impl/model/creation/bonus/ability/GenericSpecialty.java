package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class GenericSpecialty implements IGenericSpecialty {

  private final IGenericTrait trait;

  public GenericSpecialty(IGenericTrait trait) {
    this.trait = trait;
  }

  public IGenericTrait getBasicTrait() {
    return trait;
  }
}