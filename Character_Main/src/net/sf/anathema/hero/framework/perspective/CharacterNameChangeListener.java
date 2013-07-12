package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;

public interface CharacterNameChangeListener {

  void nameChanged(CharacterIdentifier identifier, String newName);
}
