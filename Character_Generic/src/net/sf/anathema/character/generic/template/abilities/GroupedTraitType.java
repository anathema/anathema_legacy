package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.character.generic.traits.ITraitType;

import java.util.Collections;
import java.util.List;

public class GroupedTraitType implements IGroupedTraitType {

  private final ITraitType type;
  private final String groupId;
  private List<String> traitCasteIds;

  private GroupedTraitType(ITraitType type, String groupId) {
    this.type = type;
    this.groupId = groupId;
  }

  public GroupedTraitType(ITraitType type, String groupId, String casteId) {
    this(type, groupId);
    if (casteId == null) {
      this.traitCasteIds = Collections.emptyList();
    } else {
      this.traitCasteIds = Collections.singletonList(casteId);
    }
  }

  public GroupedTraitType(ITraitType type, String groupId, List<String> traitCastes) {
    this(type, groupId);
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