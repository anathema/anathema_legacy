package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.IChangeListener;

public interface ISpecialtiesConfiguration {

  ISubTraitContainer getSpecialtiesContainer(ITraitReference reference);

  ISubTraitContainer getSpecialtiesContainer(TraitType traitType);

  ITraitReference[] getAllTraits();

  ITraitReference[] getAllEligibleTraits();

  void setCurrentTrait(ITraitReference newValue);

  void setCurrentSpecialtyName(String newSpecialtyName);

  void commitSelection();

  void clear();

  boolean isEntryComplete();

  boolean isExperienced();

  void addSelectionChangeListener(IChangeListener listener);

  void addTraitListChangeListener(ITraitReferencesChangeListener listener);
}