package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.IBasicLearnCharmGroup;

public interface ICharmLearnStrategy {

  boolean isUnlearnable(IBasicLearnCharmGroup group, Charm charm);

  boolean isLearned(IBasicLearnCharmGroup group, Charm charm);

  void toggleLearned(IBasicLearnCharmGroup group, Charm charm);
}