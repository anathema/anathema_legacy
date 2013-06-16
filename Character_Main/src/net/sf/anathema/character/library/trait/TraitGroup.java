package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.lib.util.Identifier;

public class TraitGroup {

  private final ITraitCollection traitCollection;
  private final IIdentifiedTraitTypeGroup groupType;

  public TraitGroup(ITraitCollection traitCollection, IIdentifiedTraitTypeGroup groupType) {
    this.traitCollection = traitCollection;
    this.groupType = groupType;
  }

  public IDefaultTrait[] getGroupTraits() {
    return traitCollection.getTraits(groupType.getAllGroupTypes());
  }

  public Identifier getGroupId() {
    return groupType.getGroupId();
  }
}