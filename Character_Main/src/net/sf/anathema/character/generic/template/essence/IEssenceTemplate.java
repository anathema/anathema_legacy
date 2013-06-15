package net.sf.anathema.character.generic.template.essence;

import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface IEssenceTemplate {

  FactorizedTrait[] getPersonalTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence);

  FactorizedTrait[] getPeripheralTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence);

  boolean isEssenceUser();
}