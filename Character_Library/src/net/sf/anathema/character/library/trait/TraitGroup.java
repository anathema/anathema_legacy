package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.lib.util.IIdentificate;

public class TraitGroup {

  private final ITraitCollection traitCollection;
  private final IIdentifiedTraitTypeGroup groupType;

  public TraitGroup(ITraitCollection traitCollection, IIdentifiedTraitTypeGroup groupType) {
    this.traitCollection = traitCollection;
    this.groupType = groupType;
  }

  public IFavorableTrait[] getGroupTraits() {
    return traitCollection.getFavorableTraits(groupType.getAllGroupTypes());
  }

  public int getInitialSum() {
    int initialSum = 0;
    for (ITrait trait : getGroupTraits()) {
      initialSum += trait.getInitialValue();
    }
    return initialSum;
  }

  public int getCreationValueSum() {
    int creationValueSum = 0;
    for (ITrait trait : getGroupTraits()) {
      creationValueSum += getModifiedTraitValue(trait);
    }
    return creationValueSum;
  }

  protected int getModifiedTraitValue(ITrait trait) {
    return trait.getCreationValue();
  }

  public IIdentificate getGroupId() {
    return groupType.getGroupId();
  }
}