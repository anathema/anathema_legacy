package net.sf.anathema.character.main.magic.charms.special;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.charms.special.ISubeffect;
import net.sf.anathema.character.main.magic.charms.special.SubEffects;
import net.sf.anathema.lib.data.Condition;

class PrerequisiteLearnCondition implements Condition {
  private final SubEffects allEffects;
  private final ICharmLearnableArbitrator arbitrator;
  private final ICharm charm;
  private final String prereqEffect;

  public PrerequisiteLearnCondition(SubEffects allEffects, ICharmLearnableArbitrator arbitrator, ICharm charm, String prereqEffect) {
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
    for (ISubeffect effect : allEffects) {
      if (effect.getId().equals(prereqEffect) && effect.isLearned()) {
        return true;
      }
    }
    return false;
  }
}