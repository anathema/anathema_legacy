package net.sf.anathema.character.generic.framework.xml.essence;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface IEssencePoolConfiguration {

  public int getEssenceMultiplier();

  public int getWillpowerMultiplier();

  public FactorizedTrait[] createVirtueFactorizedTrait(IGenericTrait[] virtues);
}