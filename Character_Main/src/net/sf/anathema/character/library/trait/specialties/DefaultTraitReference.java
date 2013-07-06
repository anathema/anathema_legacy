package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.Trait;

public class DefaultTraitReference implements ITraitReference {

  private final TraitType type;

  public DefaultTraitReference(Trait visitedTrait) {
    this.type = visitedTrait.getType();
  }

  public DefaultTraitReference(TraitType type) {
    this.type = type;
  }

  @Override
  public TraitType getTraitType() {
    return type;
  }

  @Override
  public String getName() {
    return null;
  }

  @SuppressWarnings("SimplifiableIfStatement")
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