package net.sf.anathema.character.perspective;

import net.sf.anathema.framework.repository.IItem;

public interface CharacterStackBridge {

  void addCharacterView(IItem item);

  void showCharacterView(String repositoryId);
}
