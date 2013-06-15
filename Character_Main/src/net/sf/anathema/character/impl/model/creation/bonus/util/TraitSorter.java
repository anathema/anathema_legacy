package net.sf.anathema.character.impl.model.creation.bonus.util;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

import java.util.Collection;

public class TraitSorter extends WeightedObjectSorter<ITrait> {

  private static class WeightedAttributeGroup extends WeightedObject<ITrait> {
    public WeightedAttributeGroup(ITrait value, int weight) {
      super(value, weight);
    }
  }

  @Override
  public WeightedObject<ITrait>[] convertToArray(Collection<WeightedObject<ITrait>> collection) {
    return collection.toArray(new WeightedAttributeGroup[collection.size()]);
  }

  @Override
  public WeightedObject<ITrait> createWeightedObject(ITrait value, int weight) {
    return new WeightedAttributeGroup(value, weight);
  }
}