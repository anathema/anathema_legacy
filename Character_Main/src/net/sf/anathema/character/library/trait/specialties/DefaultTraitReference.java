package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.Trait;

public class DefaultTraitReference implements ITraitReference {

  private final ITraitType type;

  public DefaultTraitReference(Trait visitedTrait) {
    this.type = visitedTrait.getType();
  }

  @Override
  public ITraitType getTraitType() {
    return type;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DefaultTraitReference)) {
      return false;
    }
    return type == ((DefaultTraitReference) obj).type;
  }

  @Override
  public int hashCode() {
    return type.hashCode();
  }

  @Override
  public String toString() {
    return type.getId();
  }
}