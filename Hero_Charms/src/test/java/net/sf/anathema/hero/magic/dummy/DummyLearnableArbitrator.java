package net.sf.anathema.hero.magic.dummy;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.ICharmLearnListener;
import net.sf.anathema.hero.charms.model.IExtendedCharmLearnableArbitrator;
import org.apache.commons.lang3.ArrayUtils;

public class DummyLearnableArbitrator implements IExtendedCharmLearnableArbitrator {

  private final String[] learnableCharmIds;

  public DummyLearnableArbitrator(String... learnableCharmIds) {
    this.learnableCharmIds = learnableCharmIds;
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return ArrayUtils.contains(learnableCharmIds, charm.getId());
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    // Nothing to do
  }

  @Override
  public boolean isLearned(Charm charm) {
    return false;
  }

  @Override
  public boolean isCompulsiveCharm(Charm charm) {
    return false;
  }
}