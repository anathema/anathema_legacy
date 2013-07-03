package net.sf.anathema.character.generic.template.essence;

import net.sf.anathema.character.generic.traits.ValuedTraitType;

public interface IEssenceTemplate {

  FactorizedTrait[] getPersonalTraits(ValuedTraitType willpower, ValuedTraitType[] virtues, ValuedTraitType essence);

  FactorizedTrait[] getPeripheralTraits(ValuedTraitType willpower, ValuedTraitType[] virtues, ValuedTraitType essence);

  boolean isEssenceUser();
}