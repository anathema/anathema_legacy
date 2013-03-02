package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;

public interface CharacterNameChangeListener {

  void nameChanged(CharacterIdentifier identifier, String newName);
}
