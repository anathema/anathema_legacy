package net.sf.anathema.character.main.xml.essence;

import net.sf.anathema.character.main.template.essence.FactorizedTrait;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface IVirtuePoolPart {

  FactorizedTrait createFactorizedTrait(ValuedTraitType[] virtues);
}