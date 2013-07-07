package net.sf.anathema.character.main.magic.charms.special;

public interface IUpgradableCharmConfiguration extends IMultipleEffectCharmConfiguration {
  int getUpgradeBPCost();

  int getUpgradeXPCost();
}