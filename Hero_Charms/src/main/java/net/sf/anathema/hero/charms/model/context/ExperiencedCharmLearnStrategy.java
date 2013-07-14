package net.sf.anathema.hero.charms.model.context;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnStrategy;
import net.sf.anathema.character.main.magic.model.charms.IBasicLearnCharmGroup;

public class ExperiencedCharmLearnStrategy implements ICharmLearnStrategy {

  @Override
  public boolean isUnlearnable(IBasicLearnCharmGroup group, Charm charm) {
    return group.isLearned(charm, true);
  }

  @Override
  public boolean isLearned(IBasicLearnCharmGroup group, Charm charm) {
    return group.isLearned(charm, false) || group.isLearned(charm, true);
  }

  @Override
  public void toggleLearned(IBasicLearnCharmGroup group, Charm charm) {
    group.toggleExperienceLearnedCharm(charm);
  }
}