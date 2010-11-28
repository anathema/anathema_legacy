package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;

public class CharmLearnAdapter implements ICharmLearnListener {

  public void charmLearned(ICharm charm) {
    // Nothing to do
  }

  public void charmForgotten(ICharm charm) {
    // Nothing to do
  }

  public void charmNotLearnable(ICharm charm) {
    // Nothing to do
  }

  public void charmNotUnlearnable(ICharm charm) {
    // Nothing to do
  }

  public void recalculateRequested() {
    // Nothing to do    
  }
}