package net.sf.anathema.character.main.model.abilities;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface AbilityModel extends TraitMap{

  Identifier ID = new SimpleIdentifier("Abilities");

  Trait[] getAll();

  Trait getTrait(TraitType type);

  IIdentifiedTraitTypeGroup[] getAbilityTypeGroups();

  SpecialtiesModel getSpecialtyConfiguration();
}
