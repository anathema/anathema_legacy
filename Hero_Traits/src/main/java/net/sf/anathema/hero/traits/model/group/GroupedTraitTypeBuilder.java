package net.sf.anathema.hero.traits.model.group;

import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.TraitTypeList;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.hero.traits.template.Group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupedTraitTypeBuilder {

  public static GroupedTraitType[] BuildFor(GroupedTraitsTemplate template, TraitTypeList traitTypelist) {
    GroupedTraitTypeBuilder builder = new GroupedTraitTypeBuilder(traitTypelist);
    for (Group group : template.groups) {
      for (String traitId : group.traits) {
        builder.addGroupedTraitType(traitId, group.id, group.casteId);
      }
    }
    return builder.getTraitTypeGroups();
  }

  private List<GroupedTraitType> groupedTraitTypes = new ArrayList<>();
  private final TraitTypeList traitTypeList;

  public GroupedTraitTypeBuilder(TraitTypeList traitTypelist) {
    this.traitTypeList = traitTypelist;
  }

  public GroupedTraitType[] getTraitTypeGroups() {
    return groupedTraitTypes.toArray(new GroupedTraitType[groupedTraitTypes.size()]);
  }

  public void addGroupedTraitType(String traitId, String groupId, String traitCaste) {
    TraitType traitType = traitTypeList.getById(traitId);
    List<String> traitCasteList = traitCaste == null ? new ArrayList<>() : Collections.singletonList(traitCaste);
    GroupedTraitType type = new GroupedTraitType(traitType, groupId, traitCasteList);
    groupedTraitTypes.add(type);
  }
}