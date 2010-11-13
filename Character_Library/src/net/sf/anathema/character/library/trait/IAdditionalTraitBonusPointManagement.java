package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface IAdditionalTraitBonusPointManagement {
  public void spendOn(IGenericTrait trait, int bonusCost);
}