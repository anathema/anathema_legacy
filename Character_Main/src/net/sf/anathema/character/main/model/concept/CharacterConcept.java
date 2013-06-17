package net.sf.anathema.character.main.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CharacterConcept {

  public static final Identifier ID = new SimpleIdentifier("Concept");

  ITypedDescription<ICasteType> getCaste();

  IIntegerDescription getAge();
}