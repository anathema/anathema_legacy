package net.sf.anathema.character.equipment;

import net.sf.anathema.lib.util.IIdentificate;

public enum MagicalMaterial implements IIdentificate {

  None, Variable, Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel, Compound;

  public String getId() {
    return name();
  }
}