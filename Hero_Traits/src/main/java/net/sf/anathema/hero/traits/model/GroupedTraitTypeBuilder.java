package net.sf.anathema.hero.traits.model;

import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.AllAbilityTraitTypeList;
import net.sf.anathema.character.main.traits.lists.TraitTypeList;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.hero.traits.template.Group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupedTraitTypeBuilder {

  public static GroupedTraitType[] BuildFor(GroupedTraitsTemplate template) {
    GroupedTraitTypeBuilder builder = new GroupedTraitTypeBuilder();
    for (Group group : template.groups) {
      for (String traitId : group.traits) {
        builder.addGroupedTraitType(traitId, group.id, group.casteId);
      }
    }
    return builder.getTraitTypeGroups();
  }

  private List<GroupedTraitType> groupedTraitTypes = new ArrayList<>();
  private final TraitTypeList traitTypeGroup = AllAbilityTraitTypeList.getInstance();

  public GroupedTraitType[] getTraitTypeGroups() {
    return groupedTraitTypes.toArray(new GroupedTraitType[groupedTraitTypes.size()]);
  }

  public void addGroupedTraitType(String traitId, String groupId, String traitCaste) {
    TraitType traitType = traitTypeGroup.getById(traitId);
    List<String> traitCasteList = traitCaste == null ? new ArrayList<>() : Collections.singletonList(traitCaste);
    GroupedTraitType type = new GroupedTraitType(traitType, groupId, traitCasteList);
    groupedTraitTypes.add(type);
  }
}