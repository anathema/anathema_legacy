package net.sf.anathema.character.generic.template.essence;

import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface IEssenceTemplate {

  public FactorizedTrait[] getPersonalTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence);

  public FactorizedTrait[] getPeripheralTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence);

  public boolean isEssenceUser();
}