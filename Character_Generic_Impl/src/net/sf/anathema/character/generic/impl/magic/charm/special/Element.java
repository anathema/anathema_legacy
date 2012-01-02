package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.caste.ICasteType;

enum Element {
  Air, Earth, Fire, Water, Wood;

  public boolean matches(ICasteType caste) {
    return caste.getId().equals(name());
  }
}