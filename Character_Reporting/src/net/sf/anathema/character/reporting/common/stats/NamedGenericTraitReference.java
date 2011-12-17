package net.sf.anathema.character.reporting.common.stats;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class NamedGenericTraitReference implements IValuedTraitReference {

  private final String name;
  private final ITraitType type;
  private final int value;

  public NamedGenericTraitReference(INamedGenericTrait trait, ITraitType type) {
    this.type = type;
    this.name = trait.getName();
    this.value = trait.getCurrentValue();
  }

  public String getName() {
    return name;
  }

  public ITraitType getTraitType() {
    return type;
  }

  public int getValue() {
    return value;
  }
}
