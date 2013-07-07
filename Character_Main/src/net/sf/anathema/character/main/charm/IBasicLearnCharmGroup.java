package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.main.magic.ICharm;

public interface IBasicLearnCharmGroup {

  boolean isLearned(ICharm charm);

  boolean isLearned(ICharm charm, boolean experienced);

  void toggleLearnedOnCreation(ICharm charm);

  void toggleExperienceLearnedCharm(ICharm charm);
}