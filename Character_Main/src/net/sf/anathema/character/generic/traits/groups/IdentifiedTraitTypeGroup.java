package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identified;

public class IdentifiedTraitTypeGroup extends TraitTypeGroup implements IIdentifiedTraitTypeGroup {

  private final Identified groupId;

  public IdentifiedTraitTypeGroup(ITraitType[] traitTypes, Identified groupId) {
    super(traitTypes);
    this.groupId = groupId;
  }

  @Override
  public Identified getGroupId() {
    return groupId;
  }
}