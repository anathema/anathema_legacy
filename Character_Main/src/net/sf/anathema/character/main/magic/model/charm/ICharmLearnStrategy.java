package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.charms.IBasicLearnCharmGroup;

public interface ICharmLearnStrategy {

  boolean isUnlearnable(IBasicLearnCharmGroup group, Charm charm);

  boolean isLearned(IBasicLearnCharmGroup group, Charm charm);

  void toggleLearned(IBasicLearnCharmGroup group, Charm charm);
}