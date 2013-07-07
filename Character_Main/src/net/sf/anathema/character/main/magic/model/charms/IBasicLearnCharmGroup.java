package net.sf.anathema.character.main.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface IBasicLearnCharmGroup {

  boolean isLearned(ICharm charm);

  boolean isLearned(ICharm charm, boolean experienced);

  void toggleLearnedOnCreation(ICharm charm);

  void toggleExperienceLearnedCharm(ICharm charm);
}