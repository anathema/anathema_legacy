package net.sf.anathema.character.main.template.essence;

import net.sf.anathema.character.main.traits.ValuedTraitType;

public interface IEssenceTemplate {

  FactorizedTrait[] getPersonalTraits(ValuedTraitType willpower, ValuedTraitType[] virtues, ValuedTraitType essence);

  FactorizedTrait[] getPeripheralTraits(ValuedTraitType willpower, ValuedTraitType[] virtues, ValuedTraitType essence);

  boolean isEssenceUser();
}