package net.sf.anathema.hero.magic.model.charms.context;

import net.sf.anathema.character.main.magic.charms.IBasicLearnCharmGroup;
import net.sf.anathema.character.main.magic.charms.ICharmLearnStrategy;
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