package net.sf.anathema.character.main.perspective;

import net.sf.anathema.character.main.perspective.model.CharacterIdentifier;

public interface CharacterNameChangeListener {

  void nameChanged(CharacterIdentifier identifier, String newName);
}
