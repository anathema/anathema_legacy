package net.sf.anathema.character.generic.util;

import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

import java.util.Collection;

public class GenericTraitSorter extends WeightedObjectSorter<ValuedTraitType> {

  private static class WeightedGenericTrait extends WeightedObject<ValuedTraitType> {
    public WeightedGenericTrait(ValuedTraitType value, int weight) {
      super(value, weight);
    }
  }

  @Override
  public WeightedObject<ValuedTraitType>[] convertToArray(Collection<WeightedObject<ValuedTraitType>> collection) {
    return collection.toArray(new WeightedGenericTrait[collection.size()]);
  }

  @Override
  public WeightedObject<ValuedTraitType> createWeightedObject(ValuedTraitType value, int weight) {
    return new WeightedGenericTrait(value, weight);
  }
}