package net.sf.anathema.character.generic.traits.groups;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.traits.ITraitType;


public class TraitTypeGroup implements ITraitTypeGroup {
  
  private final ITraitType[] traitTypes;

  public TraitTypeGroup(ITraitType[] traitTypes) {
    this.traitTypes = traitTypes;
  }

  public final ITraitType getById(String typeId) {
    for (int index = 0; index < traitTypes.length; index++) {
      if (traitTypes[index].getId().equals(typeId)) {
        return traitTypes[index];
      }
    }
    throw new IllegalArgumentException("No trait type with found in group with id " + typeId); //$NON-NLS-1$
  }

  public final ITraitType[] getAllGroupTypes() {
    return traitTypes;
  }
  
  public final boolean contains(ITraitType traitType) {
    return ArrayUtilities.contains(traitTypes, traitType);
  }
}