package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicLearnCharmGroup;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.magic.ICharm;

public class ExperiencedCharmLearnStrategy implements ICharmLearnStrategy {

  @Override
  public boolean isUnlearnable(IBasicLearnCharmGroup group, ICharm charm) {
    return group.isLearned(charm, true);
  }

  @Override
  public boolean isLearned(IBasicLearnCharmGroup group, ICharm charm) {
    return group.isLearned(charm, false) || group.isLearned(charm, true);
  }

  @Override
  public void toggleLearned(IBasicLearnCharmGroup group, ICharm charm) {
    group.toggleExperienceLearnedCharm(charm);
  }
}