package net.sf.anathema.character.main.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public interface IBasicLearnCharmGroup {

  boolean isLearned(Charm charm);

  boolean isLearned(Charm charm, boolean experienced);

  void toggleLearnedOnCreation(Charm charm);

  void toggleExperienceLearnedCharm(Charm charm);
}