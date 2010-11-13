package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.IIdentificate;

public class GroupedTraitType implements IGroupedTraitType {

  private final ITraitType type;
  private final String groupId;
  private final String casteId;

  public GroupedTraitType(ITraitType type, IIdentificate identificate, String casteId) {
    this(type, identificate.getId(), casteId);
  }

  public GroupedTraitType(ITraitType type, String groupId, String casteId) {
    this.type = type;
    this.groupId = groupId;
    this.casteId = casteId;
  }

  public ITraitType getTraitType() {
    return type;
  }

  public String getGroupId() {
    return groupId;
  }

  public String getCasteId() {
    return casteId;
  }
}