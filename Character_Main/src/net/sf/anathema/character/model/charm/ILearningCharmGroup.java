package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicLearnCharmGroup;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;

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