package net.sf.anathema.hero.charms.model.special.upgradable;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.CharmSpecialist;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecialsImpl;

public class UpgradableCharmConfiguration extends MultipleEffectCharmSpecialsImpl implements IUpgradableCharmConfiguration {

  public UpgradableCharmConfiguration(CharmSpecialist specialist, Charm charm, IUpgradableCharm upgradableCharm,
                                      ICharmLearnableArbitrator arbitrator) {
    super(specialist, charm, upgradableCharm, arbitrator);
  }

  @Override
  protected UpgradableSubEffects getSubeffects() {
    return (UpgradableSubEffects) super.getSubeffects();
  }

  @Override
  public void forget() {
    //nothing to do
  }

  @Override
  public void learn(boolean experienced) {
    //nothing to do
  }

  @Override
  public int getUpgradeBPCost() {
    return getSubeffects().getUpgradeBPCost();
  }

  @Override
  public int getUpgradeXPCost() {
    return getSubeffects().getUpgradeXPCost();
  }
}