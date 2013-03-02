package net.sf.anathema.character.perspective.model.model;

import net.sf.anathema.framework.repository.IItem;

import java.util.Collection;

public interface ItemSystemModel extends ItemSelectionModel {

  Collection<CharacterModel> collectAllExistingCharacters();

  IItem loadItem(CharacterIdentifier identifier);

  void setCurrentCharacter(CharacterIdentifier identifier);
}
