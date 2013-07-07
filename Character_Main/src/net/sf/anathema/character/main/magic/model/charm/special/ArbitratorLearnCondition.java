package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.lib.data.Condition;

class ArbitratorLearnCondition implements Condition {
  private final ICharmLearnableArbitrator arbitrator;
  private final ICharm charm;

  public ArbitratorLearnCondition(ICharmLearnableArbitrator arbitrator, ICharm charm) {
    this.arbitrator = arbitrator;
    this.charm = charm;
  }

  @Override
  public boolean isFulfilled() {
    return arbitrator.isLearnable(charm);
  }
}