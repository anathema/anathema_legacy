package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.util.IIdentificate;

public enum MartialArtsLevel implements IIdentificate {
  Mortal, Terrestrial, Celestial, Sidereal;

  public String getId() {
    return name();
  }
}