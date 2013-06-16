package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class IdentifiedCasteTraitTypeGroup extends IdentifiedTraitTypeGroup implements IIdentifiedCasteTraitTypeGroup {

  private final MultiEntryMap<TraitType, ICasteType> castesByTrait;

  public IdentifiedCasteTraitTypeGroup(TraitType[] traitTypes, Identifier groupId,
                                       MultiEntryMap<TraitType, ICasteType> castesByTrait) {
    super(traitTypes, groupId);
    this.castesByTrait = castesByTrait;
  }

  @Override
  public ICasteType[] getTraitCasteTypes(TraitType traitType) {
    TraitType[] types = getAllGroupTypes();
    for (TraitType type : types) {
      if (type == traitType) {
        List<ICasteType> casteTypes = castesByTrait.get(traitType);
        return casteTypes.toArray(new ICasteType[casteTypes.size()]);
      }
    }
    return new ICasteType[0];
  }
}