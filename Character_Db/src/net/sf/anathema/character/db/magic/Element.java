package net.sf.anathema.character.db.magic;

import net.sf.anathema.lib.util.Identified;

enum Element {
  Air, Earth, Fire, Water, Wood;

  public boolean matches(Identified caste) {
    return caste.getId().equals(name());
  }
}