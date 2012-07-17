package net.sf.anathema.character.generic.dummy.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.lib.collection.ArrayUtilities;

public class DummyLearnableArbitrator implements IExtendedCharmLearnableArbitrator {

  private final String[] learnableCharmIds;

  public DummyLearnableArbitrator(String... learnableCharmIds) {
    this.learnableCharmIds = learnableCharmIds;
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    return ArrayUtilities.containsValue(learnableCharmIds, charm.getId());
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    // Nothing to do
  }

  @Override
  public boolean isLearned(ICharm charm) {
    return false;
  }

  @Override
  public boolean isCompulsiveCharm(ICharm charm) {
    return false;
  }
}