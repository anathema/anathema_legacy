package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;

public interface ICharacterConcept {

  ITypedDescription<ICasteType> getCaste();

  IIntegerDescription getAge();
}