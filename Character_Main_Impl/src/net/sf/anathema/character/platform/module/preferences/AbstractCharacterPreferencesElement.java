package net.sf.anathema.character.platform.module.preferences;

import net.sf.anathema.lib.util.Identifier;

public abstract class AbstractCharacterPreferencesElement implements ICharacterPreferencesElement {

  @Override
  public Identifier getCategory() {
    return CHARACTER_CATEGORY;
  }
}