package net.sf.anathema.character.generic.framework.xml.essence;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.traits.GenericTrait;

public interface IEssencePoolConfiguration {

  int getEssenceMultiplier();

  int getWillpowerMultiplier();

  FactorizedTrait[] createVirtueFactorizedTrait(GenericTrait[] virtues);
}