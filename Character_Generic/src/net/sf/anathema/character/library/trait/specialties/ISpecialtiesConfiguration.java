package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.IChangeListener;

public interface ISpecialtiesConfiguration {

  ISubTraitContainer getSpecialtiesContainer(ITraitReference reference);

  ISubTraitContainer getSpecialtiesContainer(ITraitType traitType);

  ITraitReference[] getAllTraits();

  ITraitReference[] getAllEligibleTraits();

  void setCurrentTrait(ITraitReference newValue);

  void setCurrentSpecialtyName(String newSpecialtyName);

  void commitSelection();

  void clear();

  boolean isEntryComplete();

  boolean isExperienced();

  void addCharacterChangeListener(ICharacterChangeListener listener);

  void addSelectionChangeListener(IChangeListener listener);

  void addTraitListChangeListener(ITraitReferencesChangeListener listener);
}