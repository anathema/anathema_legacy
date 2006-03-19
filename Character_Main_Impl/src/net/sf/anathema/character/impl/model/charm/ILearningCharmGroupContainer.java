package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public interface ILearningCharmGroupContainer {

  public ILearningCharmGroup getLearningCharmGroup(ICharm charm);
}