package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
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

  protected final int getModifiedTraitValue(ITrait trait) {
    final int[] creationValueSum = new int[1];
    trait.accept(new ITraitVisitor() {
      public void visitDefaultTrait(IDefaultTrait visitedTrait) {
        creationValueSum[0] = visitedTrait.getCreationValue();
      }

      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        creationValueSum[0] = visitedTrait.getSubTraits().getCreationDotTotal();
      }
    });
    return creationValueSum[0];
  }

  public IIdentificate getGroupId() {
    return groupType.getGroupId();
  }
}