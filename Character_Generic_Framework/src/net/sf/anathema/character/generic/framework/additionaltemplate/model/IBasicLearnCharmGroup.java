package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IBasicLearnCharmGroup {

  boolean isLearned(ICharm charm);

  boolean isLearned(ICharm charm, boolean experienced);

  void toggleLearnedOnCreation(ICharm charm);

  void toggleExperienceLearnedCharm(ICharm charm);
}