package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class IdentifiedCasteTraitTypeGroup extends IdentifiedTraitTypeGroup implements IIdentifiedCasteTraitTypeGroup {

  private final MultiEntryMap<TraitType, CasteType> castesByTrait;

  public IdentifiedCasteTraitTypeGroup(TraitType[] traitTypes, Identifier groupId,
                                       MultiEntryMap<TraitType, CasteType> castesByTrait) {
    super(traitTypes, groupId);
    this.castesByTrait = castesByTrait;
  }

  @Override
  public CasteType[] getTraitCasteTypes(TraitType traitType) {
    for (TraitType type : getAll()) {
      if (type == traitType) {
        List<CasteType> casteTypes = castesByTrait.get(traitType);
        return casteTypes.toArray(new CasteType[casteTypes.size()]);
      }
    }
    return new CasteType[0];
  }
}