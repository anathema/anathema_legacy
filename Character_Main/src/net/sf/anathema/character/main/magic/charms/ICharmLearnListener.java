package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.character.main.magic.ICharm;

public interface ICharmLearnListener {

  void charmLearned(ICharm charm);

  void charmForgotten(ICharm charm);

  void charmNotLearnable(ICharm charm);

  void charmNotUnlearnable(ICharm charm);

  void recalculateRequested();
}