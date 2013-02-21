package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identified;

public class IdentifiedCasteTraitTypeGroup extends IdentifiedTraitTypeGroup implements IIdentifiedCasteTraitTypeGroup {

  private final ICasteType[][] traitCasteTypes;

  public IdentifiedCasteTraitTypeGroup(ITraitType[] traitTypes, Identified groupId, ICasteType[][] traitCasteTypes) {
    super(traitTypes, groupId);
    this.traitCasteTypes = traitCasteTypes;
  }

  @Override
  public ICasteType[] getTraitCasteTypes(ITraitType traitType) {
    ITraitType[] types = getAllGroupTypes();
    for (int i = 0; i != types.length; i++) {
      if (types[i] == traitType) {
        return traitCasteTypes[i];
      }
    }
    return null;
  }
}