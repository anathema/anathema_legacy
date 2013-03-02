package net.sf.anathema.character.platform.module.preferences;

import net.sf.anathema.lib.util.Identified;

public abstract class AbstractCharacterPreferencesElement implements ICharacterPreferencesElement {

  @Override
  public Identified getCategory() {
    return CHARACTER_CATEGORY;
  }
}