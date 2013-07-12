package net.sf.anathema.hero.perspective;

import net.sf.anathema.hero.perspective.model.CharacterIdentifier;
import net.sf.anathema.framework.repository.Item;

public interface CharacterStackBridge {

  void addViewForCharacter(CharacterIdentifier identifier, Item item);

  void showCharacterView(CharacterIdentifier identifier);
}
