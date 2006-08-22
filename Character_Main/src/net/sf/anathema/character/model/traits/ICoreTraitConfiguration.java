package net.sf.anathema.character.model.traits;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICoreTraitConfiguration extends ITraitCollection {
  
  public ISpecialtiesConfiguration getSpecialtyConfiguration();

  public IBackgroundConfiguration getBackgrounds();

  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups();

  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups();

  public IFavorableTrait[] getAllAbilities();

  public IIdentificate getAbilityGroupId(AbilityType abilityType);
}