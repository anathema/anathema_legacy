package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

class ArbitratorLearnCondition implements ICondition {
  private final ICharmLearnableArbitrator arbitrator;
  private final ICharm charm;

  public ArbitratorLearnCondition(ICharmLearnableArbitrator arbitrator, ICharm charm) {
    this.arbitrator = arbitrator;
    this.charm = charm;
  }

  public boolean isFulfilled() {
    return arbitrator.isLearnable(charm);
  }
}