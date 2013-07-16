package net.sf.anathema.character.main.magic.charm;

public interface ICharmLearnListener {

  void charmLearned(Charm charm);

  void charmForgotten(Charm charm);

  void charmNotLearnable(Charm charm);

  void charmNotUnlearnable(Charm charm);

  void recalculateRequested();
}