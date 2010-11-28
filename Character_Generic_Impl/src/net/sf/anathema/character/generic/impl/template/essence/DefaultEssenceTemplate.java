package net.sf.anathema.character.generic.impl.template.essence;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public final class DefaultEssenceTemplate implements IEssenceTemplate {

  public FactorizedTrait[] getPeripheralTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence) {
    return new FactorizedTrait[] {
        new FactorizedTrait(essence, 7),
        new FactorizedTrait(willpower, 1),
        new FactorizedTrait(virtues, 1) };
  }

  public FactorizedTrait[] getPersonalTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence) {
    return new FactorizedTrait[] { new FactorizedTrait(essence, 3), new FactorizedTrait(willpower, 1) };
  }

  public boolean isEssenceUser() {
    return true;
  }
}