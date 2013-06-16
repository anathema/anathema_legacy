package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.lib.util.Identifier;

public class DefaultTraitGroup implements TraitGroup {

  private final ITraitCollection traitCollection;
  private final IIdentifiedTraitTypeGroup groupType;

  public DefaultTraitGroup(ITraitCollection traitCollection, IIdentifiedTraitTypeGroup groupType) {
    this.traitCollection = traitCollection;
    this.groupType = groupType;
  }

  @Override
  public Trait[] getGroupTraits() {
    return traitCollection.getTraits(groupType.getAllGroupTypes());
  }

  @Override
  public Identifier getGroupId() {
    return groupType.getGroupId();
  }
}