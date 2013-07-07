package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.main.magic.ICharm;

public interface ILearningCharmGroupContainer {

  ILearningCharmGroup getLearningCharmGroup(ICharm charm);
}