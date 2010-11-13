package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public final class SiderealEssenceTemplate implements IEssenceTemplate {

  public FactorizedTrait[] getPeripheralTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence) {
    return new FactorizedTrait[] {
        new FactorizedTrait(essence, 6),
        new FactorizedTrait(willpower, 1),
        new FactorizedTrait(virtues, 1) };
  }

  public FactorizedTrait[] getPersonalTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence) {
    return new FactorizedTrait[] { new FactorizedTrait(essence, 2), new FactorizedTrait(willpower, 1) };
  }

  public boolean isEssenceUser() {
    return true;
  }
}