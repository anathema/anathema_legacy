package net.sf.anathema.character.sidereal.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum SiderealCaste implements ICasteType {

  Journeys, Serenity, Battles, Secrets, Endings;

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}