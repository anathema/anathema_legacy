package net.sf.anathema.character.main.magic.charm.special;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.lib.data.Condition;

class PrerequisiteLearnCondition implements Condition {
  private final SubEffects allEffects;
  private final ICharmLearnableArbitrator arbitrator;
  private final Charm charm;
  private final String prereqEffect;

  public PrerequisiteLearnCondition(SubEffects allEffects, ICharmLearnableArbitrator arbitrator, Charm charm, String prereqEffect) {
    this.allEffects = allEffects;
    this.arbitrator = arbitrator;
    this.charm = charm;
    this.prereqEffect = prereqEffect;
  }

  @Override
  public boolean isFulfilled() {
    if (!arbitrator.isLearnable(charm)) {
      return false;
    }
    if (prereqEffect == null) {
      return true;
    }
    for (SubEffect effect : allEffects) {
      if (effect.getId().equals(prereqEffect) && effect.isLearned()) {
        return true;
      }
    }
    return false;
  }
}