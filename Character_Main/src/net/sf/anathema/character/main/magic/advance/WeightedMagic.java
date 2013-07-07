package net.sf.anathema.character.main.magic.advance;

import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.lib.compare.WeightedObject;

public class WeightedMagic extends WeightedObject<IMagic> {

  public WeightedMagic(IMagic magic, int weight) {
    super(magic, weight);
  }
}