package net.sf.anathema.hero.magic.model.charms.context;

import net.sf.anathema.character.main.magic.model.charms.IBasicLearnCharmGroup;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnStrategy;
import net.sf.anathema.character.main.magic.model.charm.ICharm;

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