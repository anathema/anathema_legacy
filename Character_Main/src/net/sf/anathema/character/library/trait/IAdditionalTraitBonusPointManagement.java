package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.ValuedTraitType;

public interface IAdditionalTraitBonusPointManagement {
  void spendOn(ValuedTraitType trait, int bonusCost);
}