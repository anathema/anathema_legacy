package net.sf.anathema.character.generic.util;

import java.util.Collection;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

public class GenericTraitSorter extends WeightedObjectSorter<IGenericTrait> {

  private static class WeightedGenericTrait extends WeightedObject<IGenericTrait> {
    public WeightedGenericTrait(IGenericTrait value, int weight) {
      super(value, weight);
    }
  }

  @Override
  public WeightedObject<IGenericTrait>[] convertToArray(Collection<WeightedObject<IGenericTrait>> collection) {
    return collection.toArray(new WeightedGenericTrait[collection.size()]);
  }

  @Override
  public WeightedObject<IGenericTrait> createWeightedObject(IGenericTrait value, int weight) {
    return new WeightedGenericTrait(value, weight);
  }
}