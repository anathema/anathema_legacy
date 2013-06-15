package net.sf.anathema.character.perspective.model;

import net.sf.anathema.framework.repository.IItem;

import java.util.Collection;

public interface ItemSystemModel extends ItemSelectionModel {

  Collection<CharacterItemModel> collectAllExistingCharacters();

  IItem loadItem(CharacterIdentifier identifier);

  void setCurrentCharacter(CharacterIdentifier identifier);
}