package net.sf.anathema.character.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;

public interface ICharacterConcept {

  IMotivation getWillpowerRegainingConcept();

  ITypedDescription<ICasteType> getCaste();
  
  IIntegerDescription getAge();
}