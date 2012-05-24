package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.util.Identified;

public enum MartialArtsLevel implements Identified {
  Mortal, Terrestrial, Celestial, Sidereal;

  @Override
  public String getId() {
    return name();
  }
}