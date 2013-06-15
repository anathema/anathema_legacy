package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ICharacterDescription extends CharacterModel {

  Identifier ID = new Identifier("Description");

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