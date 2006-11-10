package net.sf.anathema.character.generic.template.essence;

import net.sf.anathema.character.generic.traits.IGenericTrait;

public class FactorizedTrait {

  private final IGenericTrait[] traits;
  private final int factor;

  public FactorizedTrait(IGenericTrait[] traits, int factor) {
    this.traits = traits;
    this.factor = factor;
  }

  public FactorizedTrait(IGenericTrait trait, int factor) {
    this(new IGenericTrait[] { trait }, factor);
  }

  public int getCalculateTotal() {
    int sum = 0;
    for (IGenericTrait trait : traits) {
      sum += trait.getCurrentValue();
    }
    return sum * factor;
  }
}