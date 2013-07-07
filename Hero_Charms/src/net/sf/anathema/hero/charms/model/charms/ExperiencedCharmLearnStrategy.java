package net.sf.anathema.hero.charms.model.charms;

import net.sf.anathema.character.main.charm.IBasicLearnCharmGroup;
import net.sf.anathema.character.main.charm.ICharmLearnStrategy;
import net.sf.anathema.character.main.magic.ICharm;

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