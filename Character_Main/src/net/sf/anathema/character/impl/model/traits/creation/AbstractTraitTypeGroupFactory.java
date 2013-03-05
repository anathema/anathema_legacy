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
import java.util.List;

public abstract class AbstractTraitTypeGroupFactory {

  protected abstract Identified getGroupIdentifier(ICasteCollection casteCollection, String groupId);

  public IIdentifiedCasteTraitTypeGroup[] createTraitGroups(ICasteCollection casteCollection,
                                                            GroupedTraitType[] traitTypes) {
    List<String> groupIds = new ArrayList<>();
    MultiEntryMap<String, ITraitType> traitTypesByGroupId = new MultiEntryMap<>();
    for (GroupedTraitType type : traitTypes) {
      String groupId = type.getGroupId();
      if (!groupIds.contains(groupId)) {
        groupIds.add(groupId);
      }
      traitTypesByGroupId.add(type.getGroupId(), type.getTraitType());
    }
    IIdentifiedCasteTraitTypeGroup[] groups = new IIdentifiedCasteTraitTypeGroup[groupIds.size()];
    for (int groupIndex = 0; groupIndex < groups.length; groupIndex++) {
      String groupId = groupIds.get(groupIndex);
      List<ITraitType> groupTraitTypes = traitTypesByGroupId.get(groupId);
      List<List<ICasteType>> traitCasteSet = createTraitCasteSet(groupId, traitTypes, casteCollection);
      groups[groupIndex] = createTraitGroup(casteCollection, groupId, groupTraitTypes, traitCasteSet);
    }
    return groups;
  }

  private List<List<ICasteType>> createTraitCasteSet(String groupId, GroupedTraitType[] traitTypes,
                                                     ICasteCollection casteCollection) {
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
    return allTypeList;
  }

  private IIdentifiedCasteTraitTypeGroup createTraitGroup(ICasteCollection casteCollection, String groupId,
                                                          List<ITraitType> traitTypes,
                                                          List<List<ICasteType>> traitCasteTypes) {
    Identified groupIdentifier = getGroupIdentifier(casteCollection, groupId);
    ITraitType[] types = traitTypes.toArray(new ITraitType[traitTypes.size()]);
    return new IdentifiedCasteTraitTypeGroup(types, groupIdentifier, traitCasteTypes);
  }
}