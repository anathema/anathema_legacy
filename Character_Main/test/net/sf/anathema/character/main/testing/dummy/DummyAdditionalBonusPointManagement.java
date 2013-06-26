package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.model.creation.bonus.additional.IAdditionalBonusPointManagement;

public class DummyAdditionalBonusPointManagement implements IAdditionalBonusPointManagement {

  @Override
  public void spendOn(GenericTrait trait, int bonusCost) {
    // Nothing to do
  }

  @Override
  public void spendOn(IMagic magic, int bonusCost) {
    // Nothing to do
  }

  @Override
  public void spendOn(IGenericSpecialty[] specialties, AbilityPointCosts costs) {
    // Nothing to do
  }
}