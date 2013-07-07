package net.sf.anathema.character.main.perspective;

import net.sf.anathema.character.main.perspective.model.CharacterIdentifier;
import net.sf.anathema.framework.repository.Item;

public interface CharacterStackBridge {

  void addViewForCharacter(CharacterIdentifier identifier, Item item);

  void showCharacterView(CharacterIdentifier identifier);
}
