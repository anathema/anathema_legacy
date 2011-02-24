package net.sf.anathema.character.impl.model.traits.creation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedCasteTraitTypeGroup;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractTraitTypeGroupFactory {

  protected abstract IIdentificate getGroupIdentifier(ICasteCollection casteCollection, String groupId);

  public IIdentifiedCasteTraitTypeGroup[] createTraitGroups(
      ICasteCollection casteCollection,
      IGroupedTraitType[] traitTypes) {
    List<String> groupIds = new ArrayList<String>();
    MultiEntryMap<String, ITraitType> traitTypesByGroupId = new MultiEntryMap<String, ITraitType>();
    Map<String, String> casteIdByGroupId = new HashMap<String, String>();
    for (IGroupedTraitType type : traitTypes) {
      String groupId = type.getGroupId();
      casteIdByGroupId.put(type.getGroupId(), type.getGroupCasteId());
      if (!groupIds.contains(groupId)) {
        groupIds.add(groupId);
      }
      traitTypesByGroupId.add(type.getGroupId(), type.getTraitType());
    }
    IIdentifiedCasteTraitTypeGroup[] groups = new IIdentifiedCasteTraitTypeGroup[groupIds.size()];
    for (int groupIndex = 0; groupIndex < groups.length; groupIndex++) {
      String groupId = groupIds.get(groupIndex);
      String casteId = casteIdByGroupId.get(groupId);
      groups[groupIndex] = createTraitGroup(casteCollection, groupId, casteId, traitTypesByGroupId.get(groupId),
    		  createTraitCasteSet(groupId, traitTypesByGroupId.get(groupId).size(), traitTypes, casteCollection));
    }
    return groups;
  }
  
  private ICasteType[][] createTraitCasteSet(String groupId, int size, IGroupedTraitType[] traitTypes, ICasteCollection casteCollection)
  {
	  ICasteType[][] traitCasteSet = new ICasteType[size][];
	  int index = 0;
      for (IGroupedTraitType type : traitTypes)
      {
    	  List<String> traitCasteNames = type.getTraitCasteSet();
    	  if ((traitCasteNames != null || type.getGroupCasteId() != null) && type.getGroupId().equals(groupId))
    	  {
    		  boolean includeTraitCaste = type.getTraitCasteSet() != null;
    		  boolean includeGroupCaste = type.getGroupCasteId() != null;
    		  traitCasteSet[index] = new ICasteType[(includeTraitCaste ? type.getTraitCasteSet().size() : 0)
    		                                        + (includeGroupCaste ? 1 : 0)];
    		  int subIndex = 0;
    		  if (includeTraitCaste)
	    		  for (; subIndex != traitCasteNames.size(); subIndex++)
	    			  traitCasteSet[index][subIndex] = casteCollection.getById(traitCasteNames.get(subIndex));
    		  if (includeGroupCaste)
    			  traitCasteSet[index][subIndex] = casteCollection.getById(type.getGroupCasteId());
    		  index++;
    	  }
      }
      return traitCasteSet;
  }

  private IIdentifiedCasteTraitTypeGroup createTraitGroup(
      ICasteCollection casteCollection,
      String groupId,
      String casteId,
      List<ITraitType> traitTypes,
      ICasteType[][] traitCasteTypes) {
    ICasteType casteType = casteCollection.containsCasteType(casteId) ? casteCollection.getById(casteId) : null;
    IIdentificate groupIdentifier = getGroupIdentifier(casteCollection, groupId);
    return new IdentifiedCasteTraitTypeGroup(
        traitTypes.toArray(new ITraitType[traitTypes.size()]),
        groupIdentifier,
        casteType,
        traitCasteTypes);
  }

}
