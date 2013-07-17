package net.sf.anathema.hero.charms.model.special.upgradable;

import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectImpl;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.lib.data.Condition;

public class Upgrade extends SubEffectImpl {
  private int bpCost;
  private int xpCost;

  public Upgrade(String subeffectId, ExperienceModel experience, Condition learnable, int bpCost, int xpCost) {
    super(subeffectId, experience, learnable);
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