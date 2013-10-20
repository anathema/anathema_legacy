package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.charm.prerequisite.DirectCharmLearnPrerequisite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirectGroupCharmLearnPrerequisite implements DirectCharmLearnPrerequisite {

  private final int threshold;
  private final String[] prerequisiteIds;
  private Charm[] prerequisites;


  public DirectGroupCharmLearnPrerequisite(String[] charms, int threshold) {
    this.prerequisiteIds = charms;
    this.threshold = threshold;
  }

  @Override
  public boolean isSatisfied(ICharmLearnArbitrator arbitrator) {
    int known = 0;
    for (Charm charm : prerequisites) {
      if (arbitrator.isLearned(charm)) {
        known++;
      }
      if (known >= threshold) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void link(Map<String, CharmImpl> charmsById) {
    if (prerequisites != null) {
      return;
    }
    List<Charm> prerequisites = new ArrayList<>();
    for (String id : prerequisiteIds) {
      Charm parentCharm = charmsById.get(id);
      Preconditions.checkNotNull(parentCharm, "Parent Charm " + id + " not defined.");
      prerequisites.add(parentCharm);
    }
    this.prerequisites = prerequisites.toArray(new Charm[prerequisites.size()]);
  }

  @Override
  public Charm[] getDirectPredecessors() {
    return prerequisites;
  }

  @Override
  public boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator) {
    int knowable = 0;
    for (Charm charm : prerequisites) {
      if (arbitrator.isLearnable(charm)) {
        knowable++;
      }
      if (knowable >= threshold) {
        return true;
      }
    }
    return false;
  }

  public Charm[] getLearnPrerequisites(ICharmLearnArbitrator learnArbitrator) {
    Set<Charm> prerequisiteCharms = new LinkedHashSet<>();
    List<Charm> charmsToLearn = selectCharmsToLearn(learnArbitrator);
    for (Charm learnCharm : charmsToLearn) {
      prerequisiteCharms.addAll(learnCharm.getLearnPrerequisitesCharms(learnArbitrator));
      prerequisiteCharms.add(learnCharm);
    }
    return prerequisiteCharms.toArray(new Charm[prerequisiteCharms.size()]);
  }

  private List<Charm> selectCharmsToLearn(ICharmLearnArbitrator learnArbitrator) {
    List<Charm> charmsToLearn = new ArrayList<>();
    for (Charm charm : getDirectPredecessors()) {
      if (charmsToLearn.size() >= threshold) {
        return charmsToLearn;
      }
      if (learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    for (Charm charm : getDirectPredecessors()) {
      if (charmsToLearn.size() >= threshold) {
        return charmsToLearn;
      }
      if (!learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    return charmsToLearn;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DirectGroupCharmLearnPrerequisite) {
      DirectGroupCharmLearnPrerequisite prerequisite = (DirectGroupCharmLearnPrerequisite) obj;
      return Arrays.deepEquals(prerequisites, prerequisite.prerequisites) && prerequisite.threshold == threshold;
    }
    return false;
  }
}
