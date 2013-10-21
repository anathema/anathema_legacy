package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;

public interface CharacterStackBridge {

  void addViewForCharacter(CharacterIdentifier identifier, Item item);

  void showCharacterView(CharacterIdentifier identifier);
}
