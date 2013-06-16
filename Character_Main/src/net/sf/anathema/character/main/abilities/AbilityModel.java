package net.sf.anathema.character.main.abilities;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.main.traits.model.TraitMap;

public interface AbilityModel extends TraitMap{

  Trait[] getAll();

  TraitGroup[] getTraitGroups();

  Trait getTrait(TraitType type);

  IIdentifiedTraitTypeGroup[] getAbilityTypeGroups();

  ISpecialtiesConfiguration getSpecialtyConfiguration();
}
