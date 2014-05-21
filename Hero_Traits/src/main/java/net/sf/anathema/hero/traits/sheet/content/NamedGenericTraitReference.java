package net.sf.anathema.hero.traits.sheet.content;

import net.sf.anathema.hero.specialties.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;

public class NamedGenericTraitReference implements ValuedTraitReference {

  private final String name;
  private final TraitType type;
  private final int value;

  public NamedGenericTraitReference(Specialty trait, TraitType type) {
    this.type = type;
    this.name = trait.getName();
    this.value = trait.getCurrentValue();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public TraitType getTraitType() {
    return type;
  }

  @Override
  public int getValue() {
    return value;
  }
}
