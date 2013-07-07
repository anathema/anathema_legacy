package net.sf.anathema.character.main.template.essence;

import net.sf.anathema.character.main.traits.ValuedTraitType;

public class FactorizedTrait {

  private final ValuedTraitType[] traits;
  private final int factor;

  public FactorizedTrait(ValuedTraitType[] traits, int factor) {
    this.traits = traits;
    this.factor = factor;
  }

  public FactorizedTrait(ValuedTraitType trait, int factor) {
    this(new ValuedTraitType[] { trait }, factor);
  }

  public int getCalculateTotal() {
    int sum = 0;
    for (ValuedTraitType trait : traits) {
      sum += trait.getCurrentValue();
    }
    return sum * factor;
  }
}