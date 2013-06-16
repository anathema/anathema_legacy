package net.sf.anathema.character.generic.impl.template.essence;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.GenericTrait;

public class NullEssenceTemplate implements IEssenceTemplate {

  @Override
  public FactorizedTrait[] getPersonalTraits(GenericTrait willpower, GenericTrait[] virtues, GenericTrait essence) {
    return new FactorizedTrait[0];
  }

  @Override
  public FactorizedTrait[] getPeripheralTraits(GenericTrait willpower, GenericTrait[] virtues, GenericTrait essence) {
    return new FactorizedTrait[0];
  }

  @Override
  public boolean isEssenceUser() {
    return false;
  }
}