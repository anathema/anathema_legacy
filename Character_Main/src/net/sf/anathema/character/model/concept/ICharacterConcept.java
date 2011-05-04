package net.sf.anathema.character.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public interface ICharacterConcept {

  public IWillpowerRegainingConcept getWillpowerRegainingConcept();

  public ITypedDescription<ICasteType> getCaste();
  
  public IntegerSpinner getAgeSpinner();
}