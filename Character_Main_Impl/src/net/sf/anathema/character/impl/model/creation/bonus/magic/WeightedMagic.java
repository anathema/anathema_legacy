package net.sf.anathema.character.impl.model.creation.bonus.magic;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.compare.WeightedObject;

public class WeightedMagic extends WeightedObject<IMagic> {

  public WeightedMagic(IMagic magic, int weight) {
    super(magic, weight);
  }
}