package net.sf.anathema.character.model;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ICharacterDescription {

  ITextualDescription getName();

  ITextualDescription getPeriphrase();

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