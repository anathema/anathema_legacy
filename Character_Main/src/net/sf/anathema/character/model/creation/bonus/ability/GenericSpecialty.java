package net.sf.anathema.character.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.traits.GenericTrait;

public class GenericSpecialty implements IGenericSpecialty {

  private final GenericTrait trait;

  public GenericSpecialty(GenericTrait trait) {
    this.trait = trait;
  }

  @Override
  public GenericTrait getBasicTrait() {
    return trait;
  }
}