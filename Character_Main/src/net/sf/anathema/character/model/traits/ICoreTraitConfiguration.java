package net.sf.anathema.character.model.traits;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;

public interface ICoreTraitConfiguration extends ITraitCollection {

  ISpecialtiesConfiguration getSpecialtyConfiguration();

  void addFavorableTraits(IIdentifiedCasteTraitTypeGroup[] traitGroups, IIncrementChecker incrementChecker, TypedTraitTemplateFactory factory);

  IIdentifiedTraitTypeGroup[] getAbilityTypeGroups();
}