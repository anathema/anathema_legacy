package net.sf.anathema.character.impl.model.creation.bonus.util;

import java.util.Collection;

import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

public class TraitSorter extends WeightedObjectSorter<IFavorableTrait> {

  private static class WeightedAttributeGroup extends WeightedObject<IFavorableTrait> {
    public WeightedAttributeGroup(IFavorableTrait value, int weight) {
      super(value, weight);
    }
  }

  @Override
  public WeightedObject<IFavorableTrait>[] convertToArray(Collection<WeightedObject<IFavorableTrait>> collection) {
    return collection.toArray(new WeightedAttributeGroup[collection.size()]);
  }

  @Override
  public WeightedObject<IFavorableTrait> createWeightedObject(IFavorableTrait value, int weight) {
    return new WeightedAttributeGroup(value, weight);
  }
}