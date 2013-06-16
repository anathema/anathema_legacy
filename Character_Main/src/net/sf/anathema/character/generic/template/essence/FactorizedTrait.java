package net.sf.anathema.character.generic.template.essence;

import net.sf.anathema.character.generic.traits.GenericTrait;

public class FactorizedTrait {

  private final GenericTrait[] traits;
  private final int factor;

  public FactorizedTrait(GenericTrait[] traits, int factor) {
    this.traits = traits;
    this.factor = factor;
  }

  public FactorizedTrait(GenericTrait trait, int factor) {
    this(new GenericTrait[] { trait }, factor);
  }

  public int getCalculateTotal() {
    int sum = 0;
    for (GenericTrait trait : traits) {
      sum += trait.getCurrentValue();
    }
    return sum * factor;
  }
}