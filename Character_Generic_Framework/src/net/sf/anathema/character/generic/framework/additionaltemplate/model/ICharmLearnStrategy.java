package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmLearnStrategy {

  boolean isUnlearnable(IBasicLearnCharmGroup group, ICharm charm);

  boolean isLearned(IBasicLearnCharmGroup group, ICharm charm);

  void toggleLearned(IBasicLearnCharmGroup group, ICharm charm);
}