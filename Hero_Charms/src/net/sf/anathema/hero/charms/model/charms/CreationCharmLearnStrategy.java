package net.sf.anathema.hero.charms.model.charms;

import net.sf.anathema.character.main.charm.IBasicLearnCharmGroup;
import net.sf.anathema.character.main.charm.ICharmLearnStrategy;
import net.sf.anathema.character.main.magic.ICharm;

public class CreationCharmLearnStrategy implements ICharmLearnStrategy {

  @Override
  public boolean isUnlearnable(IBasicLearnCharmGroup group, ICharm charm) {
    return group.isLearned(charm);
  }

  @Override
  public boolean isLearned(IBasicLearnCharmGroup group, ICharm charm) {
    return group.isLearned(charm, false);
  }

  @Override
  public void toggleLearned(IBasicLearnCharmGroup group, ICharm charm) {
    group.toggleLearnedOnCreation(charm);
  }
}