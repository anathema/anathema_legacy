package net.sf.anathema.hero.magic.model.charms.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.MultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.UpgradableSubEffects;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharm;
import net.sf.anathema.character.main.magic.model.charm.CharmSpecialist;

public class UpgradableCharmConfiguration extends MultipleEffectCharmConfiguration implements IUpgradableCharmConfiguration {

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