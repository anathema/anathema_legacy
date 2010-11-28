package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IBasicLearnCharmGroup {

  public boolean isLearned(ICharm charm);

  public boolean isLearned(ICharm charm, boolean experienced);

  public void toggleLearnedOnCreation(ICharm charm);

  public void toggleExperienceLearnedCharm(ICharm charm);
}