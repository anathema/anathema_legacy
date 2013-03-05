package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedCasteTraitTypeGroup;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractTraitTypeGroupFactory {

  protected abstract Identified getGroupIdentifier(ICasteCollection casteCollection, String groupId);

  public IIdentifiedCasteTraitTypeGroup[] createTraitGroups(ICasteCollection casteCollection,
                                                            GroupedTraitType[] traitTypes) {
    Set<String> groupIds = new LinkedHashSet<>();
    MultiEntryMap<String, ITraitType> traitTypesByGroupId = new MultiEntryMap<>();
    for (GroupedTraitType type : traitTypes) {
      String groupId = type.getGroupId();
      groupIds.add(groupId);
      traitTypesByGroupId.add(groupId, type.getTraitType());
    }
    List<IIdentifiedCasteTraitTypeGroup> groups = new ArrayList<>();
    for (String groupId : groupIds) {
      List<ITraitType> groupTraitTypes = traitTypesByGroupId.get(groupId);
      List<List<ICasteType>> allTypeList = new ArrayList<>();
      for (GroupedTraitType type : traitTypes) {
        if (!type.getGroupId().equals(groupId)) {
          continue;
        }
        List<ICasteType> currentTypeList = new ArrayList<>();
        allTypeList.add(currentTypeList);
        for (String casteTypeId : type.getTraitCasteSet()) {
          ICasteType casteType = casteCollection.getById(casteTypeId);
          currentTypeList.add(casteType);
        }
      }
      Identified groupIdentifier = getGroupIdentifier(casteCollection, groupId);
      ITraitType[] types = groupTraitTypes.toArray(new ITraitType[groupTraitTypes.size()]);
      groups.add(new IdentifiedCasteTraitTypeGroup(types, groupIdentifier, allTypeList));
    }
    return groups.toArray(new IIdentifiedCasteTraitTypeGroup[groups.size()]);
  }
}