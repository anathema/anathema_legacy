package net.sf.anathema.hero.magic.model;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.lib.compare.WeightedObject;

public class WeightedMagic extends WeightedObject<Magic> {

  public WeightedMagic(Magic magic, int weight) {
    super(magic, weight);
  }
}