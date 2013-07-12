package net.sf.anathema.hero.attributes.advance.creation;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

import java.util.Collection;

public class TraitSorter extends WeightedObjectSorter<Trait> {

  private static class WeightedAttributeGroup extends WeightedObject<Trait> {
    public WeightedAttributeGroup(Trait value, int weight) {
      super(value, weight);
    }
  }

  @Override
  public WeightedObject<Trait>[] convertToArray(Collection<WeightedObject<Trait>> collection) {
    return collection.toArray(new WeightedAttributeGroup[collection.size()]);
  }

  @Override
  public WeightedObject<Trait> createWeightedObject(Trait value, int weight) {
    return new WeightedAttributeGroup(value, weight);
  }
}