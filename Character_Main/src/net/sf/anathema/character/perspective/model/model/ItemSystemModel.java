package net.sf.anathema.character.perspective.model.model;

import net.sf.anathema.character.perspective.CharacterNameChangeListener;
import net.sf.anathema.character.perspective.DistinctiveFeatures;
import net.sf.anathema.framework.repository.IItem;

import java.util.Collection;

public interface ItemSystemModel extends ItemSelectionModel {

  Collection<DistinctiveFeatures> collectAllExistingCharacters();

  IItem loadItem(CharacterIdentifier identifier);

  void setCurrentCharacter(CharacterIdentifier identifier);

  void whenCurrentSelectionChangesName(CharacterNameChangeListener listener);
}
