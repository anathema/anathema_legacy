package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.lib.data.Condition;

public class Upgrade extends Subeffect {
  private int bpCost;
  private int xpCost;

  public Upgrade(String subeffectId, IBasicCharacterData data, Condition learnable, int bpCost, int xpCost) {
    super(subeffectId, data, learnable);
    this.bpCost = bpCost;
    this.xpCost = xpCost;
  }

  public int getBPCost() {
    return bpCost;
  }

  public int getXPCost() {
    return xpCost;
  }
}