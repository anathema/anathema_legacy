package net.sf.anathema.character.reporting.pdf.content.stats;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;

public class NamedGenericTraitReference implements IValuedTraitReference {

  private final String name;
  private final ITraitType type;
  private final int value;

  public NamedGenericTraitReference(Specialty trait, ITraitType type) {
    this.type = type;
    this.name = trait.getName();
    this.value = trait.getCurrentValue();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ITraitType getTraitType() {
    return type;
  }

  @Override
  public int getValue() {
    return value;
  }
}
