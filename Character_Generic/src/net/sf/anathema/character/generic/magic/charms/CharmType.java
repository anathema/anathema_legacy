package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.util.IIdentificate;

public enum CharmType implements IIdentificate {
  Simple, ExtraAction, Reflexive, Supplemental, Special;

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}