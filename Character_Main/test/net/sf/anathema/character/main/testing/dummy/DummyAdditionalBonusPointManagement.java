package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;
import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.model.creation.bonus.additional.AdditionalBonusPoints;

public class DummyAdditionalBonusPointManagement implements AdditionalBonusPoints {

  @Override
  public void spendOn(ValuedTraitType trait, int bonusCost) {
    // Nothing to do
  }

  @Override
  public void spendOn(IMagic magic, int bonusCost) {
    // Nothing to do
  }

  @Override
  public void reset() {
    // Nothing to do
  }

  @Override
  public int getPointSpent() {
    return 0;
  }

  @Override
  public int getAmount() {
    return 0;
  }

  @Override
  public void spendOn(IGenericSpecialty[] specialties, AbilityPointCosts costs) {
    // Nothing to do
  }
}