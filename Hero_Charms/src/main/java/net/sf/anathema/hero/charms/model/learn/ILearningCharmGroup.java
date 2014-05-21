package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.IBasicLearnCharmGroup;
import net.sf.anathema.hero.charms.model.ICharmGroup;

public interface ILearningCharmGroup extends ICharmGroup, IBasicLearnCharmGroup {

  void toggleLearned(Charm charm);

  void addCharmLearnListener(ICharmLearnListener listener);

  Charm[] getCreationLearnedCharms();

  void learnCharm(Charm charm, boolean experienced);

  void learnCharmNoParents(Charm charm, boolean experienced, boolean announce);

  boolean isUnlearnable(Charm charm);

  boolean isUnlearnableWithoutConsequences(Charm charm);

  Charm[] getExperienceLearnedCharms();

  void forgetCharm(Charm child, boolean experienced);

  void forgetAll();

  boolean hasLearnedCharms();

  Charm[] getCoreCharms();

  void unlearnExclusives();

  void fireRecalculateRequested();
}