package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.charm.Charm;

public class CharmLearnAdapter implements ICharmLearnListener {

  @Override
  public void charmLearned(Charm charm) {
    // Nothing to do
  }

  @Override
  public void charmForgotten(Charm charm) {
    // Nothing to do
  }

  @Override
  public void charmNotLearnable(Charm charm) {
    // Nothing to do
  }

  @Override
  public void charmNotUnlearnable(Charm charm) {
    // Nothing to do
  }

  @Override
  public void recalculateRequested() {
    // Nothing to do    
  }
}