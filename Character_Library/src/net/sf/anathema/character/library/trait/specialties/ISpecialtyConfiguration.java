package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.specialty.ISpecialtiesContainer;

public interface ISpecialtyConfiguration {

  public ISpecialtiesContainer getSpecialtiesContainer(ITraitType traitType);

  public ITraitType[] getAllTraitTypes();
}