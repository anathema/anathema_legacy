package net.sf.anathema.character.impl.module.preferences;

import net.sf.anathema.lib.util.Identified;

public abstract class AbstractCharacterPreferencesElement implements ICharacterPreferencesElement {

  @Override
  public Identified getCategory() {
    return CHARACTER_CATEGORY;
  }
}