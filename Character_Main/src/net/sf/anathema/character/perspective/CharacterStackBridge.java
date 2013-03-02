package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.framework.repository.IItem;

public interface CharacterStackBridge {

  void addViewForCharacter(CharacterIdentifier identifier, IItem item);

  void showCharacterView(CharacterIdentifier identifier);
}
