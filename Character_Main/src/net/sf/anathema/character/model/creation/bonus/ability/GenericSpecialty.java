package net.sf.anathema.character.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.traits.ValuedTraitType;

public class GenericSpecialty implements IGenericSpecialty {

  private final ValuedTraitType trait;

  public GenericSpecialty(ValuedTraitType trait) {
    this.trait = trait;
  }

  @Override
  public ValuedTraitType getBasicTrait() {
    return trait;
  }
}