package net.sf.anathema.character.generic.impl.template.essence;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.data.Range;

public class NullEssenceTemplate implements IEssenceTemplate {

  public FactorizedTrait[] getPersonalTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence) {
    return new FactorizedTrait[0];
  }

  public FactorizedTrait[] getPeripheralTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence) {
    return new FactorizedTrait[0];
  }

  public Range getEssenceRange() {
    return new Range(1, 1);
  }

  public boolean isEssenceUser() {
    return false;
  }
}