package net.sf.anathema.character.main.magic.advance;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.lib.compare.WeightedObject;
import net.sf.anathema.lib.compare.WeightedObjectSorter;

import java.util.Collection;

public class WeightedMagicSorter extends WeightedObjectSorter<Magic> {

  @Override
  public WeightedObject<Magic> createWeightedObject(Magic magic, int weight) {
    return new WeightedMagic(magic, weight);
  }

  @Override
  public WeightedObject<Magic>[] convertToArray(Collection<WeightedObject<Magic>> collection) {
    return collection.toArray(new WeightedMagic[collection.size()]);
  }
}