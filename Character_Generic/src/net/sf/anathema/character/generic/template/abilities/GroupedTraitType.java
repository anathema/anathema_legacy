package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.character.generic.traits.ITraitType;

import java.util.List;

public class GroupedTraitType implements IGroupedTraitType {

  private final ITraitType type;
  private final String groupId;
  private final List<String> traitCasteIds;

  public GroupedTraitType(ITraitType type, String groupId, List<String> traitCastes) {
    this.type = type;
    this.groupId = groupId;
    this.traitCasteIds = traitCastes;
  }

  @Override
  public ITraitType getTraitType() {
    return type;
  }

  @Override
  public String getGroupId() {
    return groupId;
  }

  @Override
  public List<String> getTraitCasteSet() {
    return traitCasteIds;
  }
}