package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.character.magic.charm.Charm;

public interface ICharmLearnListener {

  void charmLearned(Charm charm);

  void charmForgotten(Charm charm);

  void charmNotLearnable(Charm charm);

  void charmNotUnlearnable(Charm charm);

  void recalculateRequested();
}