package net.sf.anathema.character.main.description.model;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface CharacterDescription {

  SimpleIdentifier ID = new SimpleIdentifier("Description");

  ITextualDescription getName();

  ITextualDescription getPeriphrasis();

  ITextualDescription getCharacterization();

  ITextualDescription getPhysicalDescription();

  ITextualDescription getEyes();

  ITextualDescription getHair();

  ITextualDescription getSex();

  ITextualDescription getSkin();

  ITextualDescription getAnima();

  ITextualDescription getNotes();

  ITextualDescription getPlayer();

  ITextualDescription getConcept();

  void addOverallChangeListener(ObjectValueListener<String> listener);
}