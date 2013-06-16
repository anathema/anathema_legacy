package net.sf.anathema.character.generic.template.essence;

import net.sf.anathema.character.generic.traits.GenericTrait;

public interface IEssenceTemplate {

  FactorizedTrait[] getPersonalTraits(GenericTrait willpower, GenericTrait[] virtues, GenericTrait essence);

  FactorizedTrait[] getPeripheralTraits(GenericTrait willpower, GenericTrait[] virtues, GenericTrait essence);

  boolean isEssenceUser();
}