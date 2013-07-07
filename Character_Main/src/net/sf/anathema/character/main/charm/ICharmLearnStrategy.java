package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.main.magic.ICharm;

public interface ICharmLearnStrategy {

  boolean isUnlearnable(IBasicLearnCharmGroup group, ICharm charm);

  boolean isLearned(IBasicLearnCharmGroup group, ICharm charm);

  void toggleLearned(IBasicLearnCharmGroup group, ICharm charm);
}