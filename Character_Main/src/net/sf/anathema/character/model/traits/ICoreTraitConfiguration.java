package net.sf.anathema.character.model.traits;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICoreTraitConfiguration extends ITraitCollection {

  ISpecialtiesConfiguration getSpecialtyConfiguration();

  IBackgroundConfiguration getBackgrounds();

  IIdentifiedTraitTypeGroup[] getAttributeTypeGroups();

  IIdentifiedTraitTypeGroup[] getAbilityTypeGroups();
  
  IIdentifiedTraitTypeGroup[] getYoziTypeGroups();

  IFavorableTrait[] getAllAbilities();

  IIdentificate getAbilityGroupId(AbilityType abilityType);
}