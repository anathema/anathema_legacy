package net.sf.anathema.hero.magic.model.charms.context;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charms.IBasicLearnCharmGroup;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnStrategy;

public class CreationCharmLearnStrategy implements ICharmLearnStrategy {

  @Override
  public boolean isUnlearnable(IBasicLearnCharmGroup group, Charm charm) {
    return group.isLearned(charm);
  }

  @Override
  public boolean isLearned(IBasicLearnCharmGroup group, Charm charm) {
    return group.isLearned(charm, false);
  }

  @Override
  public void toggleLearned(IBasicLearnCharmGroup group, Charm charm) {
    group.toggleLearnedOnCreation(charm);
  }
}