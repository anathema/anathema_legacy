package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.character.generic.traits.ITraitType;

import java.util.List;

public class GroupedTraitType {

  private final ITraitType type;
  private final String groupId;
  private final List<String> traitCasteIds;

  public GroupedTraitType(ITraitType type, String groupId, List<String> traitCastes) {
    this.type = type;
    this.groupId = groupId;
    this.traitCasteIds = traitCastes;
  }

  public ITraitType getTraitType() {
    return type;
  }

  public String getGroupId() {
    return groupId;
  }

  public List<String> getTraitCasteSet() {
    return traitCasteIds;
  }
}