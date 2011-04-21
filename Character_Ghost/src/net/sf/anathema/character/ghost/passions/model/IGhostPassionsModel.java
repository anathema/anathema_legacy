package net.sf.anathema.character.ghost.passions.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.specialties.ITraitReferencesChangeListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IGhostPassionsModel extends IAdditionalModel {

  public ISubTraitContainer getPassionContainer(ITraitReference reference);

  public ISubTraitContainer getPassionContainer(ITraitType traitType);

  public ITraitReference[] getAllTraits();
	  
  public ITraitReference[] getAllEligibleTraits();

  public void setCurrentTrait(ITraitReference newValue);

  public void setCurrentPassionName(String newPassionName);

  public void commitSelection();

  public void clear();

  public boolean isEntryComplete();

  public boolean isExperienced();

  public void addCharacterChangeListener(ICharacterChangeListener listener);

  public void addSelectionChangeListener(IChangeListener listener);

  public void addTraitListChangeListener(ITraitReferencesChangeListener listener);
  
  public int getCurrentVirtueRating(VirtueType type);
  
  public int getCurrentTotalPassions();
  
  public int getMaxTotalPassions();

}