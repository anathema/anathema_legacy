package net.sf.anathema.character.main.magic.advance;

import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

import java.util.Collection;

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