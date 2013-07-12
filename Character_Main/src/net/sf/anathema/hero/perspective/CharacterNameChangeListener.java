package net.sf.anathema.hero.perspective;

import net.sf.anathema.hero.perspective.model.CharacterIdentifier;

public interface CharacterNameChangeListener {

  void nameChanged(CharacterIdentifier identifier, String newName);
}
