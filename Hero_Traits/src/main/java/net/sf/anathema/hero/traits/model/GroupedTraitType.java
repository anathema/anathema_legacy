package net.sf.anathema.hero.traits.model;

import net.sf.anathema.character.main.traits.TraitType;

import java.util.List;

public class GroupedTraitType {

  private final TraitType type;
  private final String groupId;
  private final List<String> traitCasteIds;

  public GroupedTraitType(TraitType type, String groupId, List<String> traitCastes) {
    this.type = type;
    this.groupId = groupId;
    this.traitCasteIds = traitCastes;
  }

  public TraitType getTraitType() {
    return type;
  }

  public String getGroupId() {
    return groupId;
  }

  public List<String> getTraitCasteSet() {
    return traitCasteIds;
  }
}