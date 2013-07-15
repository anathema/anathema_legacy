package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.character.main.template.creation.IGenericSpecialty;
import net.sf.anathema.character.main.traits.ValuedTraitType;

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