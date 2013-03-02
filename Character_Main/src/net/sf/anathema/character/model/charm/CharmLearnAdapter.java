package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;

public class CharmLearnAdapter implements ICharmLearnListener {

  @Override
  public void charmLearned(ICharm charm) {
    // Nothing to do
  }

  @Override
  public void charmForgotten(ICharm charm) {
    // Nothing to do
  }

  @Override
  public void charmNotLearnable(ICharm charm) {
    // Nothing to do
  }

  @Override
  public void charmNotUnlearnable(ICharm charm) {
    // Nothing to do
  }

  @Override
  public void recalculateRequested() {
    // Nothing to do    
  }
}