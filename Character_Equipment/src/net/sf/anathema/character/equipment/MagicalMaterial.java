package net.sf.anathema.character.equipment;

import net.sf.anathema.lib.util.IIdentificate;

public enum MagicalMaterial implements IIdentificate {
  Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel;

  public String getId() {
    return name();
  }
}