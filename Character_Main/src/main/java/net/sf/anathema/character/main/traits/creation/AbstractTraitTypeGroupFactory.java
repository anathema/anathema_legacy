package net.sf.anathema.character.main.traits.creation;

import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.main.traits.groups.IdentifiedCasteTraitTypeGroup;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractTraitTypeGroupFactory {

  protected abstract Identifier getGroupIdentifier(CasteCollection casteCollection, String groupId);

  public IIdentifiedCasteTraitTypeGroup[] createTraitGroups(CasteCollection casteCollection, GroupedTraitType[] traitTypes) {
    Set<String> groupIds = new LinkedHashSet<>();
    MultiEntryMap<String, TraitType> traitTypesByGroupId = new MultiEntryMap<>();
    for (GroupedTraitType type : traitTypes) {
      String groupId = type.getGroupId();
      groupIds.add(groupId);
      traitTypesByGroupId.add(groupId, type.getTraitType());
    }
    List<IIdentifiedCasteTraitTypeGroup> groups = new ArrayList<>();
    for (String groupId : groupIds) {
      List<TraitType> groupTraitTypes = traitTypesByGroupId.get(groupId);
      MultiEntryMap<TraitType, CasteType> castesByTrait = new MultiEntryMap<>();
      for (GroupedTraitType type : traitTypes) {
        if (!type.getGroupId().equals(groupId)) {
          continue;
        }
        for (String casteTypeId : type.getTraitCasteSet()) {
          CasteType casteType = casteCollection.getById(casteTypeId);
          castesByTrait.add(type.getTraitType(), casteType);
        }
      }
      Identifier groupIdentifier = getGroupIdentifier(casteCollection, groupId);
      TraitType[] types = groupTraitTypes.toArray(new TraitType[groupTraitTypes.size()]);
      groups.add(new IdentifiedCasteTraitTypeGroup(types, groupIdentifier, castesByTrait));
    }
    return groups.toArray(new IIdentifiedCasteTraitTypeGroup[groups.size()]);
  }
}