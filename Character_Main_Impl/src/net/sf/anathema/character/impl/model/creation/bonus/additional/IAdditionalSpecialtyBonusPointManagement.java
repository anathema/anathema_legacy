package net.sf.anathema.character.impl.model.creation.bonus.additional;

import net.sf.anathema.character.generic.template.creation.IGenericSpecialty;
import net.sf.anathema.character.generic.template.experience.IAbilityPointCosts;

public interface IAdditionalSpecialtyBonusPointManagement {
  public void spendOn(IGenericSpecialty[] specialties, IAbilityPointCosts costs);
}