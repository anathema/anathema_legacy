package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

enum Element {
  Air, Earth, Fire, Water, Wood;

  public boolean matches(IIdentificate caste) {
    return caste.getId().equals(name());
  }
}