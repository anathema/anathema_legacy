package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.magic.charm.Charm;

public interface IBasicLearnCharmGroup {

  boolean isLearned(Charm charm);

  boolean isLearned(Charm charm, boolean experienced);

  void toggleLearnedOnCreation(Charm charm);

  void toggleExperienceLearnedCharm(Charm charm);
}