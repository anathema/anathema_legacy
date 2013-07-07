package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.library.trait.specialties.Specialty;

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
