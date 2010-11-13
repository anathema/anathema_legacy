package net.sf.anathema.character.impl.model.creation.bonus.util;

import java.util.Collection;

import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

public class TraitGroupSorter extends WeightedObjectSorter<TraitGroup> {

  private static class WeightedAttributeGroup extends WeightedObject<TraitGroup> {
    public WeightedAttributeGroup(TraitGroup value, int weight) {
      super(value, weight);
    }
  }

  @Override
  public WeightedObject<TraitGroup>[] convertToArray(Collection<WeightedObject<TraitGroup>> collection) {
    return collection.toArray(new WeightedAttributeGroup[collection.size()]);
  }

  @Override
  public WeightedObject<TraitGroup> createWeightedObject(TraitGroup value, int weight) {
    return new WeightedAttributeGroup(value, weight);
  }
}