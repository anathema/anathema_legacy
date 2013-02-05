package net.sf.anathema.character.perspective.model.model;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.PrintNameFile;

import java.util.List;

public interface ItemSystemModel extends ItemSelectionModel {

  List<PrintNameFile> collectCharacterPrintNameFiles();

  IItem loadItem(CharacterIdentifier identifier);

  void setCurrentCharacter(CharacterIdentifier identifier);
}
