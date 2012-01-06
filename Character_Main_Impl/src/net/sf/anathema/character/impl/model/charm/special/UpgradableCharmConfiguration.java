package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.model.charm.special.IUpgradableCharmConfiguration;

public class UpgradableCharmConfiguration extends MultipleEffectCharmConfiguration implements IUpgradableCharmConfiguration {
  private final IUpgradableCharm upgrade;

  public UpgradableCharmConfiguration(ICharacterModelContext context,
                                      ICharm charm, IUpgradableCharm visited,
                                      ICharmLearnableArbitrator arbitrator) {
    super(context, charm, visited, arbitrator);
    upgrade = visited;
  }

  @Override
  public void forget() {
    //nothing to do
  }

  @Override
  public void learn(boolean experienced) {
    //nothing to do
  }

  public int getUpgradeBPCost() {
    return upgrade.getUpgradeBPCost();
  }

  public int getUpgradeXPCost() {
    return upgrade.getUpgradeXPCost();
  }
}