package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmLearnListener {

  public void charmLearned(ICharm charm);

  public void charmForgotten(ICharm charm);

  public void charmNotLearnable(ICharm charm);

  public void charmNotUnlearnable(ICharm charm);

  public void recalculateRequested();
}