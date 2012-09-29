package net.sf.anathema.character.ghost.passions.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.specialties.ITraitReferencesChangeListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.IChangeListener;

public interface IGhostPassionsModel extends IAdditionalModel {

  ISubTraitContainer getPassionContainer(ITraitReference reference);

  ISubTraitContainer getPassionContainer(ITraitType traitType);

  ITraitReference[] getAllTraits();
	  
  ITraitReference[] getAllEligibleTraits();

  void setCurrentTrait(ITraitReference newValue);

  void setCurrentPassionName(String newPassionName);

  void commitSelection();

  void clear();

  boolean isEntryComplete();

  boolean isExperienced();

  void addCharacterChangeListener(ICharacterChangeListener listener);

  void addSelectionChangeListener(IChangeListener listener);

  void addTraitListChangeListener(ITraitReferencesChangeListener listener);
  
  int getCurrentVirtueRating(VirtueType type);
  
  int getCurrentTotalPassions();
  
  int getMaxTotalPassions();
}