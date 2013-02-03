package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.framework.repository.IItem;

public interface CharacterStackBridge {

  void addViewForExistingCharacter(IItem item);

  void showCharacterView(CharacterIdentifier identifier);
}
