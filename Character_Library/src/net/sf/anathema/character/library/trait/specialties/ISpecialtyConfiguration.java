package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ISpecialtyConfiguration {

  public ISubTraitContainer getSpecialtiesContainer(ITraitType traitType);

  public ITraitType[] getAllTraitTypes();

  public void setCurrentTraitType(ITraitType newValue);

  public void setCurrentSpecialtyName(String newSpecialtyName);

  public void commitSelection();

  public void clear();

  public boolean isEntryComplete();

  public void addCurrentSelectionListener(IChangeListener listener);

  public boolean isExperienced();

  public void addCharacterChangeListener(ICharacterChangeListener listener);
}