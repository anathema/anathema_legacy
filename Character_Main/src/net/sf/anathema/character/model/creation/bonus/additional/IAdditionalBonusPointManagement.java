package net.sf.anathema.character.model.creation.bonus.additional;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.library.trait.IAdditionalTraitBonusPointManagement;

public interface IAdditionalBonusPointManagement extends IAdditionalTraitBonusPointManagement, IAdditionalSpecialtyBonusPointManagement {
  void spendOn(IMagic magic, int bonusCost);
}