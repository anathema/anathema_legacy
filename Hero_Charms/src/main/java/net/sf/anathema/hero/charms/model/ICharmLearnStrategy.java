package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface ICharmLearnStrategy {

  boolean isUnlearnable(IBasicLearnCharmGroup group, Charm charm);

  boolean isLearned(IBasicLearnCharmGroup group, Charm charm);

  void toggleLearned(IBasicLearnCharmGroup group, Charm charm);
}