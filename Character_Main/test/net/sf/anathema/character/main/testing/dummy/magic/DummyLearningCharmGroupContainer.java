package net.sf.anathema.character.main.testing.dummy.magic;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.charms.ILearningCharmGroupContainer;
import net.sf.anathema.character.main.magic.charms.LearningCharmGroup;

public class DummyLearningCharmGroupContainer implements ILearningCharmGroupContainer {

  private ILearningCharmGroup[] groups;

  public void setLearningCharmGroups(ILearningCharmGroup[] groups) {
    this.groups = groups;
  }

  @Override
  public ILearningCharmGroup getLearningCharmGroup(ICharm charm) {
    for (ILearningCharmGroup group : groups) {
      if (charm.getGroupId().equals(group.getId())) {
        return group;
      }
    }
    return null;
  }

  public void setLearningCharmGroup(LearningCharmGroup learningCharmGroup) {
    setLearningCharmGroups(new ILearningCharmGroup[]{learningCharmGroup});
  }
}