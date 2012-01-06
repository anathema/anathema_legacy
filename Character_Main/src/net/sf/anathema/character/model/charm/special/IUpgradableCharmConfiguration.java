package net.sf.anathema.character.model.charm.special;

public interface IUpgradableCharmConfiguration extends IMultipleEffectCharmConfiguration {
  int getUpgradeBPCost();

  int getUpgradeXPCost();
}