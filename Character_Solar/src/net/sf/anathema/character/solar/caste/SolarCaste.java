package net.sf.anathema.character.solar.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum SolarCaste implements ICasteType {

  Dawn, Zenith, Twilight, Night, Eclipse;

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}