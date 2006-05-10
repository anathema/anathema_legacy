package net.sf.anathema.character.generic.magic.charms.type;

import net.sf.anathema.lib.util.IIdentificate;

public enum TurnType implements IIdentificate {

  Tick, LongTick, DramaticAction;

  public String getId() {
    return name();
  }
}