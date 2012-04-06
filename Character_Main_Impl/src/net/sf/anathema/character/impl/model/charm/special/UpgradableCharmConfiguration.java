package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.model.charm.special.IUpgradableCharmConfiguration;

public class UpgradableCharmConfiguration extends MultipleEffectCharmConfiguration implements IUpgradableCharmConfiguration {
  private final IUpgradableCharm upgrade;
  private final IBasicCharacterData data;

  public UpgradableCharmConfiguration(ICharacterModelContext context,
                                      ICharm charm, IUpgradableCharm visited,
                                      ICharmLearnableArbitrator arbitrator) {
    super(context, charm, visited, arbitrator);
    this.data = context.getBasicCharacterContext();
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
    return upgrade.getUpgradeBPCost(data);
  }

  public int getUpgradeXPCost() {
    return upgrade.getUpgradeXPCost(data);
  }
}