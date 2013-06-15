package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CharacterConcept extends CharacterModel {

  public static final Identifier ID = new SimpleIdentifier("Concept");

  ITypedDescription<ICasteType> getCaste();

  IIntegerDescription getAge();
}