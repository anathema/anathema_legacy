package net.sf.anathema.character.perspective.model.model;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.PrintNameFile;

import java.util.Collection;

public interface ItemSystemModel extends ItemSelectionModel {

  Collection<PrintNameFile> collectAllCharacters();

  IItem loadItem(CharacterIdentifier identifier);

  void setCurrentCharacter(CharacterIdentifier identifier);
}
