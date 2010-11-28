package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmLearnStrategy {

  public boolean isUnlearnable(IBasicLearnCharmGroup group, ICharm charm);

  public boolean isLearned(IBasicLearnCharmGroup group, ICharm charm);

  public void toggleLearned(IBasicLearnCharmGroup group, ICharm charm);
}