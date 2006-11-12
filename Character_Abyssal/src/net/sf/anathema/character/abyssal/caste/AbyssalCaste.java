package net.sf.anathema.character.abyssal.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum AbyssalCaste implements ICasteType {

  Dusk, Midnight, Daybreak, Day, Moonshadow;

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}