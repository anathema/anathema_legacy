package net.sf.anathema.character.impl.model.creation.bonus.additional;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.library.trait.IAdditionalTraitBonusPointManagement;

public interface IAdditionalBonusPointManagment extends
    IAdditionalTraitBonusPointManagement,
    IAdditionalSpecialtyBonusPointManagement {
  public void spendOn(IMagic magic, int bonusCost);
}