package net.sf.anathema.hero.charms.model.charms.special;

import net.sf.anathema.character.main.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.character.main.charm.special.MultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.magic.charms.special.UpgradableSubEffects;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.main.charm.CharmSpecialist;

public class UpgradableCharmConfiguration extends MultipleEffectCharmConfiguration implements IUpgradableCharmConfiguration {

  public UpgradableCharmConfiguration(CharmSpecialist specialist, ICharm charm, IUpgradableCharm upgradableCharm,
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