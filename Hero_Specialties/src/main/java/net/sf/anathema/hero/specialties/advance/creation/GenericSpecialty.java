package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.hero.template.creation.IGenericSpecialty;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

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