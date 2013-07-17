package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface ICharmLearnListener {

  void charmLearned(Charm charm);

  void charmForgotten(Charm charm);

  void charmNotLearnable(Charm charm);

  void charmNotUnlearnable(Charm charm);

  void recalculateRequested();
}