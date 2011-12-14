package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.IAbilityPointCosts;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.impl.model.creation.bonus.additional.IAdditionalBonusPointManagment;

public class DummyAdditionalBonusPointManagment implements IAdditionalBonusPointManagment {

  public void spendOn(IGenericTrait trait, int bonusCost) {
    // Nothing to do
  }

  public void spendOn(IMagic magic, int bonusCost) {
    // Nothing to do
  }

  public void spendOn(IGenericSpecialty[] specialties, IAbilityPointCosts costs) {
    // Nothing to do
  }
}