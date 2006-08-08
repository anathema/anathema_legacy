package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.specialty.ISubTraitContainer;

public interface ISpecialtyConfiguration {

  public ISubTraitContainer getSpecialtiesContainer(ITraitType traitType);

  public ITraitType[] getAllTraitTypes();
}