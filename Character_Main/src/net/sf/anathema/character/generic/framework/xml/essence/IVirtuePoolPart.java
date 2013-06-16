package net.sf.anathema.character.generic.framework.xml.essence;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.traits.GenericTrait;

public interface IVirtuePoolPart {

  FactorizedTrait createFactorizedTrait(GenericTrait[] virtues);
}