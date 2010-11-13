package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentifiedTraitTypeGroup extends TraitTypeGroup implements IIdentifiedTraitTypeGroup {

  private final IIdentificate groupId;

  public IdentifiedTraitTypeGroup(ITraitType[] traitTypes, IIdentificate groupId) {
    super(traitTypes);
    this.groupId = groupId;
  }

  public IIdentificate getGroupId() {
    return groupId;
  }
}