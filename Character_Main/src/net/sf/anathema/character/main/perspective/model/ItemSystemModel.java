package net.sf.anathema.character.main.perspective.model;

import net.sf.anathema.framework.repository.Item;

import java.util.Collection;

public interface ItemSystemModel extends ItemSelectionModel {

  Collection<CharacterItemModel> collectAllExistingCharacters();

  Item loadItem(CharacterIdentifier identifier);

  void setCurrentCharacter(CharacterIdentifier identifier);
}