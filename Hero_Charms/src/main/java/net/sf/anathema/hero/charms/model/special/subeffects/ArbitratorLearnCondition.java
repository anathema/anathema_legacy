package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.lib.data.Condition;

public class ArbitratorLearnCondition implements Condition {
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