package net.sf.anathema.character.model.traits;

import net.sf.anathema.character.generic.character.TraitTypeGroups;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;

public interface ICoreTraitConfiguration extends ITraitCollection, TraitTypeGroups {

  ISpecialtiesConfiguration getSpecialtyConfiguration();

  IBackgroundConfiguration getBackgrounds();

  IFavorableTrait[] getAllAbilities();
}