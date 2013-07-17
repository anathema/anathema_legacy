package net.sf.anathema.hero.charms.model.special.upgradable;

import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecials;

public interface IUpgradableCharmConfiguration extends MultipleEffectCharmSpecials {
  int getUpgradeBPCost();

  int getUpgradeXPCost();
}