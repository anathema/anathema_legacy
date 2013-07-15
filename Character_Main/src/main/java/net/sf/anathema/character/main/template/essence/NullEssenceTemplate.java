package net.sf.anathema.character.main.template.essence;

import net.sf.anathema.character.main.traits.ValuedTraitType;

public class NullEssenceTemplate implements IEssenceTemplate {

  @Override
  public FactorizedTrait[] getPersonalTraits(ValuedTraitType willpower, ValuedTraitType[] virtues, ValuedTraitType essence) {
    return new FactorizedTrait[0];
  }

  @Override
  public FactorizedTrait[] getPeripheralTraits(ValuedTraitType willpower, ValuedTraitType[] virtues, ValuedTraitType essence) {
    return new FactorizedTrait[0];
  }

  @Override
  public boolean isEssenceUser() {
    return false;
  }
}