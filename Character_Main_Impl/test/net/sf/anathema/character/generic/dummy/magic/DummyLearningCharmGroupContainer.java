package net.sf.anathema.character.generic.dummy.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.model.charm.ILearningCharmGroupContainer;
import net.sf.anathema.character.impl.model.charm.LearningCharmGroup;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public class DummyLearningCharmGroupContainer implements ILearningCharmGroupContainer {

  private ILearningCharmGroup[] groups;

  public void setLearningCharmGroups(ILearningCharmGroup[] groups) {
    this.groups = groups;
  }

  public ILearningCharmGroup getLearningCharmGroup(ICharm charm) {
    for (ILearningCharmGroup group : groups) {
      if (charm.getGroupId().equals(group.getId())) {
        return group;
      }
    }
    return null;
  }

  public void setLearningCharmGroup(LearningCharmGroup learningCharmGroup) {
    setLearningCharmGroups(new ILearningCharmGroup[] { learningCharmGroup });
  }
}