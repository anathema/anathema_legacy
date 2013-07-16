package net.sf.anathema.hero.charmtree.type;

import net.sf.anathema.lib.util.Identifier;

public enum TurnType implements Identifier {

  Tick, LongTick, DramaticAction;

  @Override
  public String getId() {
    return name();
  }
}