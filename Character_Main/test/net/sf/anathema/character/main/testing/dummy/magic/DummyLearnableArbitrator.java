package net.sf.anathema.character.main.testing.dummy.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.IExtendedCharmLearnableArbitrator;
import org.apache.commons.lang3.ArrayUtils;

public class DummyLearnableArbitrator implements IExtendedCharmLearnableArbitrator {

  private final String[] learnableCharmIds;

  public DummyLearnableArbitrator(String... learnableCharmIds) {
    this.learnableCharmIds = learnableCharmIds;
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    return ArrayUtils.contains(learnableCharmIds, charm.getId());
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