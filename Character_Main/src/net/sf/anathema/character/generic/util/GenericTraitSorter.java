package net.sf.anathema.character.generic.util;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

import java.util.Collection;

public class GenericTraitSorter extends WeightedObjectSorter<GenericTrait> {

  private static class WeightedGenericTrait extends WeightedObject<GenericTrait> {
    public WeightedGenericTrait(GenericTrait value, int weight) {
      super(value, weight);
    }
  }

  @Override
  public WeightedObject<GenericTrait>[] convertToArray(Collection<WeightedObject<GenericTrait>> collection) {
    return collection.toArray(new WeightedGenericTrait[collection.size()]);
  }

  @Override
  public WeightedObject<GenericTrait> createWeightedObject(GenericTrait value, int weight) {
    return new WeightedGenericTrait(value, weight);
  }
}