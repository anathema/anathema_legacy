package net.sf.anathema.character.generic.framework.xml.essence;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.traits.ValuedTraitType;

public interface IEssencePoolConfiguration {

  int getEssenceMultiplier();

  int getWillpowerMultiplier();

  FactorizedTrait[] createVirtueFactorizedTrait(ValuedTraitType[] virtues);
}