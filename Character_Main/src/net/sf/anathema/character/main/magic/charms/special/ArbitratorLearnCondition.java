package net.sf.anathema.character.main.magic.charms.special;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmLearnableArbitrator;
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