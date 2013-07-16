package net.sf.anathema.character.main.magic.charm.special;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.lib.data.Condition;

class ArbitratorLearnCondition implements Condition {
  private final ICharmLearnableArbitrator arbitrator;
  private final Charm charm;

  public ArbitratorLearnCondition(ICharmLearnableArbitrator arbitrator, Charm charm) {
    this.arbitrator = arbitrator;
    this.charm = charm;
  }

  @Override
  public boolean isFulfilled() {
    return arbitrator.isLearnable(charm);
  }
}