package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.character.main.magic.ICharm;

public interface ILearningCharmGroup extends ICharmGroup, IBasicLearnCharmGroup {

  void toggleLearned(ICharm charm);

  void addCharmLearnListener(ICharmLearnListener listener);

  ICharm[] getCreationLearnedCharms();

  void learnCharm(ICharm charm, boolean experienced);

  void learnCharmNoParents(ICharm charm, boolean experienced, boolean announce);

  boolean isUnlearnable(ICharm charm);

  boolean isUnlearnableWithoutConsequences(ICharm charm);

  ICharm[] getExperienceLearnedCharms();

  void forgetCharm(ICharm child, boolean experienced);

  void forgetAll();

  boolean hasLearnedCharms();

  ICharm[] getCoreCharms();

  void unlearnExclusives();

  void fireRecalculateRequested();
}