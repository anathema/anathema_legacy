package net.sf.anathema.character.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.ITypedDescription;

public interface ICharacterConcept {

  public IWillpowerRegainingConcept getWillpowerRegainingConcept();

  public ITypedDescription<ICasteType> getCaste();
}