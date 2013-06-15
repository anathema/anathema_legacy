package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identifier;

public class IdentifiedTraitTypeGroup extends TraitTypeGroup implements IIdentifiedTraitTypeGroup {

  private final Identifier groupId;

  public IdentifiedTraitTypeGroup(ITraitType[] traitTypes, Identifier groupId) {
    super(traitTypes);
    this.groupId = groupId;
  }

  @Override
  public Identifier getGroupId() {
    return groupId;
  }
}