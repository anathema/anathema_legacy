package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.lib.resources.IResources;

public class TraitReference implements ITraitReference {

  private final ITrait trait;

  public TraitReference(ITrait trait) {
    this.trait = trait;
  }

  public ITraitType getTraitType() {
    return trait.getType();
  }

  public String createName(IResources resources) {
    //TODO NOW Proper I18n 
    return toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TraitReference)) {
      return false;
    }
    return trait == ((TraitReference) obj).trait;
  }

  @Override
  public int hashCode() {
    return trait.hashCode();
  }

  @Override
  public String toString() {
    if (trait instanceof ISubTrait) {
      return trait.getType().getId() + "(" + ((ISubTrait) trait).getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    return trait.getType().getId();
  }
}