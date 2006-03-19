package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicLearnCharmGroup;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.magic.ICharm;

public class ExperiencedCharmLearnStrategy implements ICharmLearnStrategy {

  public boolean isUnlearnable(IBasicLearnCharmGroup group, ICharm charm) {
    return group.isLearned(charm, true);
  }

  public boolean isLearned(IBasicLearnCharmGroup group, ICharm charm) {
    return group.isLearned(charm, false) || group.isLearned(charm, true);
  }

  public void toggleLearned(IBasicLearnCharmGroup group, ICharm charm) {
    group.toggleExperienceLearnedCharm(charm);
  }
}