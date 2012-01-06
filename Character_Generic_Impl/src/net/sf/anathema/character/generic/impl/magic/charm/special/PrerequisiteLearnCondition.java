package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

class PrerequisiteLearnCondition implements ICondition {
  private final ICharmLearnableArbitrator arbitrator;
  private final ICharm charm;
  private final String prereqEffect;
  private ComplexMultipleEffectCharm complexMultipleEffectCharm;

  public PrerequisiteLearnCondition(ComplexMultipleEffectCharm complexMultipleEffectCharm, ICharmLearnableArbitrator arbitrator, ICharm charm, String prereqEffect) {
    this.complexMultipleEffectCharm = complexMultipleEffectCharm;
    this.arbitrator = arbitrator;
    this.charm = charm;
    this.prereqEffect = prereqEffect;
  }

  public boolean isFulfilled() {
    if (!arbitrator.isLearnable(charm)) {
      return false;
    }
    if (prereqEffect != null) {
      for (ISubeffect effect : complexMultipleEffectCharm.effectList)
        if (effect.getId().equals(prereqEffect) && effect.isLearned())
          return true;
      return false;
    }
    return true;
  }
}