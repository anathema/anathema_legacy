package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.GenericTrait;

public interface IAdditionalTraitBonusPointManagement {
  void spendOn(GenericTrait trait, int bonusCost);
}