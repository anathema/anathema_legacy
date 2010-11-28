package net.sf.anathema.character.impl.model.creation.bonus.magic;

import java.util.Collection;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

public class WeightedMagicSorter extends WeightedObjectSorter<IMagic> {

  @Override
  public WeightedObject<IMagic> createWeightedObject(IMagic magic, int weight) {
    return new WeightedMagic(magic, weight);
  }

  @Override
  public WeightedObject<IMagic>[] convertToArray(Collection<WeightedObject<IMagic>> collection) {
    return collection.toArray(new WeightedMagic[collection.size()]);
  }
}