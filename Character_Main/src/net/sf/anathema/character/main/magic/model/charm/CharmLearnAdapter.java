package net.sf.anathema.character.main.magic.model.charm;

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