package net.sf.anathema.herotype.solar.model;

import net.sf.anathema.character.main.caste.CasteType;

public enum SolarCaste implements CasteType {

  Dawn, Zenith, Twilight, Night, Eclipse;

  @Override
  public String getId() {
    return name();
  }
}