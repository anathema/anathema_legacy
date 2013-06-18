package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.UpgradableSubEffects;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.impl.model.charm.CharmSpecialist;
import net.sf.anathema.character.model.charm.special.IUpgradableCharmConfiguration;

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