package net.sf.anathema.character.impl.model.charm.test;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ICharmLearnableArbitrator;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class DummyLearnableArbitrator implements ICharmLearnableArbitrator {

  private final String[] learnableCharmIds;

  public DummyLearnableArbitrator(String[] learnableCharmIds) {
    this.learnableCharmIds = learnableCharmIds;
  }

  public boolean isLearnable(ICharm charm) {
    return ArrayUtilities.contains(learnableCharmIds, charm.getId());
  }

  public void addCharmLearnListener(ICharmLearnListener listener) {
    // Nothing to do
  }

  public boolean isLearned(ICharm charm) {
    return false;
  }

  public boolean isCompulsiveCharm(ICharm charm) {
    return false;
  }
}