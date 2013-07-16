package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.model.Magic;
import net.sf.anathema.lib.compare.WeightedObject;

public class WeightedMagic extends WeightedObject<Magic> {

  public WeightedMagic(Magic magic, int weight) {
    super(magic, weight);
  }
}