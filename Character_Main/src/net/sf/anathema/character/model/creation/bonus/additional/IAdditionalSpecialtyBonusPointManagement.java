package net.sf.anathema.character.model.creation.bonus.additional;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.AbilityPointCosts;

public interface IAdditionalSpecialtyBonusPointManagement {
  void spendOn(IGenericSpecialty[] specialties, AbilityPointCosts costs);
}