package net.sf.anathema.character.library.trait.specialties;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;

public class SpecialtyConfiguration implements ISpecialtyConfiguration {

  private Map<ITraitType, ISubTraitContainer> specialtiesByTrait = new HashMap<ITraitType, ISubTraitContainer>();
  private final ITraitType[] traitTypes;

  public SpecialtyConfiguration(ITraitCollection traitCollection, ITraitTypeGroup[] groups) {
    this.traitTypes = TraitTypeGroup.getAllTraitTypes(groups);
    for (ITrait ability : traitCollection.getTraits(getAllTraitTypes())) {
      specialtiesByTrait.put(ability.getType(), ability.createSpecialtiesContainer());
    }
  }

  public ISubTraitContainer getSpecialtiesContainer(ITraitType traitType) {
    return specialtiesByTrait.get(traitType);
  }

  public ITraitType[] getAllTraitTypes() {
    return traitTypes;
  }
}