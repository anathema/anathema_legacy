package net.sf.anathema.character.main.magic.charm;

public interface IBasicLearnCharmGroup {

  boolean isLearned(Charm charm);

  boolean isLearned(Charm charm, boolean experienced);

  void toggleLearnedOnCreation(Charm charm);

  void toggleExperienceLearnedCharm(Charm charm);
}