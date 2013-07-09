package net.sf.anathema.character.main.magic.model.charm;

public interface ICharmLearnListener {

  void charmLearned(Charm charm);

  void charmForgotten(Charm charm);

  void charmNotLearnable(Charm charm);

  void charmNotUnlearnable(Charm charm);

  void recalculateRequested();
}