package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.AllAbilityTraitTypeList;
import net.sf.anathema.character.main.traits.lists.TraitTypeList;
import net.sf.anathema.hero.abilities.template.AbilitiesTemplate;
import net.sf.anathema.hero.abilities.template.Group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupedAbilityTypeBuilder {

  public static GroupedTraitType[] BuildFor(AbilitiesTemplate template) {
    GroupedAbilityTypeBuilder builder = new GroupedAbilityTypeBuilder();
    for (Group group : template.groups) {
      for (String traitId : group.traits) {
        builder.addGroupedAbilityType(traitId, group.id, group.casteId);
      }
    }
    return builder.getTraitTypeGroups();
  }

  private List<GroupedTraitType> groupedTraitTypes = new ArrayList<>();
  private final TraitTypeList traitTypeGroup = AllAbilityTraitTypeList.getInstance();

  public GroupedTraitType[] getTraitTypeGroups() {
    return groupedTraitTypes.toArray(new GroupedTraitType[groupedTraitTypes.size()]);
  }

  public void addGroupedAbilityType(String traitId, String groupId, String traitCaste) {
    TraitType traitType = traitTypeGroup.getById(traitId);
    List<String> traitCasteList = traitCaste == null ? new ArrayList<>() : Collections.singletonList(traitCaste);
    GroupedTraitType type = new GroupedTraitType(traitType, groupId, traitCasteList);
    groupedTraitTypes.add(type);
  }
}