package net.sf.anathema.hero.magic.dummy;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.charm.ILearningCharmGroupContainer;
import net.sf.anathema.hero.charms.model.LearningCharmGroup;

public class DummyLearningCharmGroupContainer implements ILearningCharmGroupContainer {

  private ILearningCharmGroup[] groups;

  public void setLearningCharmGroups(ILearningCharmGroup[] groups) {
    this.groups = groups;
  }

  @Override
  public ILearningCharmGroup getLearningCharmGroup(Charm charm) {
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