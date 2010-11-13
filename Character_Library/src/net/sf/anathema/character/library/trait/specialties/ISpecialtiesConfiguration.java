package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ISpecialtiesConfiguration {

  public ISubTraitContainer getSpecialtiesContainer(ITraitReference reference);

  public ISubTraitContainer getSpecialtiesContainer(ITraitType traitType);

  public ITraitReference[] getAllTraits();

  public void setCurrentTrait(ITraitReference newValue);

  public void setCurrentSpecialtyName(String newSpecialtyName);

  public void commitSelection();

  public void clear();

  public boolean isEntryComplete();

  public boolean isExperienced();

  public void addCharacterChangeListener(ICharacterChangeListener listener);

  public void addSelectionChangeListener(IChangeListener listener);

  public void addTraitListChangeListener(ITraitReferencesChangeListener listener);
}