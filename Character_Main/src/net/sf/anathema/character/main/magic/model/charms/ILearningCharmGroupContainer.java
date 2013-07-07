package net.sf.anathema.character.main.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface ILearningCharmGroupContainer {

  ILearningCharmGroup getLearningCharmGroup(ICharm charm);
}