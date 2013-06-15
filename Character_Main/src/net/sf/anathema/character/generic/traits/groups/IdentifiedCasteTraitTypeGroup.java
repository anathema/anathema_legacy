package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class IdentifiedCasteTraitTypeGroup extends IdentifiedTraitTypeGroup implements IIdentifiedCasteTraitTypeGroup {

  private final MultiEntryMap<ITraitType, ICasteType> castesByTrait;

  public IdentifiedCasteTraitTypeGroup(ITraitType[] traitTypes, Identifier groupId,
                                       MultiEntryMap<ITraitType, ICasteType> castesByTrait) {
    super(traitTypes, groupId);
    this.castesByTrait = castesByTrait;
  }

  @Override
  public ICasteType[] getTraitCasteTypes(ITraitType traitType) {
    ITraitType[] types = getAllGroupTypes();
    for (ITraitType type : types) {
      if (type == traitType) {
        List<ICasteType> casteTypes = castesByTrait.get(traitType);
        return casteTypes.toArray(new ICasteType[casteTypes.size()]);
      }
    }
    return new ICasteType[0];
  }
}