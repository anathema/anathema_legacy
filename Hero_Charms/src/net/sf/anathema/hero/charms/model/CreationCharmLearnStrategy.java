package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicLearnCharmGroup;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.magic.ICharm;

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