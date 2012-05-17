package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.lib.util.Identified;

enum Element {
  Air, Earth, Fire, Water, Wood;

  public boolean matches(Identified caste) {
    return caste.getId().equals(name());
  }
}