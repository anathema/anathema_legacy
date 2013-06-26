package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ILearningCharmGroupContainer {

  ILearningCharmGroup getLearningCharmGroup(ICharm charm);
}