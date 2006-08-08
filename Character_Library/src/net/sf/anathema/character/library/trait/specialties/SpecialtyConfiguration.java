package net.sf.anathema.character.library.trait.specialties;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.specialty.ISpecialtiesContainer;

public class SpecialtyConfiguration implements ISpecialtyConfiguration {

  private Map<ITraitType, ISpecialtiesContainer> specialtiesByTrait = new HashMap<ITraitType, ISpecialtiesContainer>();
  private final ITraitType[] traitTypes;

  public SpecialtyConfiguration(ITraitCollection traitCollection, ITraitTypeGroup[] groups) {
    this.traitTypes = TraitTypeGroup.getAllTraitTypes(groups);
    for (ITrait ability : traitCollection.getTraits(getAllTraitTypes())) {
      specialtiesByTrait.put(ability.getType(), ability.createSpecialtiesContainer());
    }
  }

  public ISpecialtiesContainer getSpecialtiesContainer(ITraitType traitType) {
    return specialtiesByTrait.get(traitType);
  }

  public ITraitType[] getAllTraitTypes() {
    return traitTypes;
  }
}