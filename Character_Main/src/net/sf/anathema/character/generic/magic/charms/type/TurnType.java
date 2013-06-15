package net.sf.anathema.character.generic.magic.charms.type;

import net.sf.anathema.lib.util.Identified;

public enum TurnType implements Identified {

  Tick, LongTick, DramaticAction;

  @Override
  public String getId() {
    return name();
  }
}