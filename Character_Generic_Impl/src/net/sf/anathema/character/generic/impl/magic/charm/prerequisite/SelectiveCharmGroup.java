package net.sf.anathema.character.generic.impl.magic.charm.prerequisite;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SelectiveCharmGroup {

  private final SelectiveCharmGroupTemplate template;
  private final List<Charm> charms = new ArrayList<>();

  public SelectiveCharmGroup(SelectiveCharmGroupTemplate template) {
    Preconditions.checkNotNull(template);
    this.template = template;
  }

  public void extractCharms(Map<String, ? extends Charm> charmsById, Charm child) {
    for (String charmId : template.getGroupCharmIds()) {
      Charm groupCharm = charmsById.get(charmId);
      Preconditions.checkNotNull(groupCharm, "Charm not found for id " + charmId);
      charms.add(groupCharm);
      groupCharm.addChild(child);
    }
  }

  public Set<ICharm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    Set<ICharm> prerequisiteCharms = new LinkedHashSet<>();
    List<Charm> charmsToLearn = selectCharmsToLearn(learnArbitrator);
    for (Charm learnCharm : charmsToLearn) {
      prerequisiteCharms.addAll(learnCharm.getLearnPrerequisitesCharms(learnArbitrator));
      prerequisiteCharms.add(learnCharm);
    }
    return prerequisiteCharms;
  }

  private List<Charm> selectCharmsToLearn(ICharmLearnArbitrator learnArbitrator) {
    List<Charm> charmsToLearn = new ArrayList<>();
    for (Charm charm : charms) {
      if (charmsToLearn.size() >= template.getThreshold()) {
        return charmsToLearn;
      }
      if (learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    for (Charm charm : charms) {
      if (charmsToLearn.size() >= template.getThreshold()) {
        return charmsToLearn;
      }
      if (!learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    return charmsToLearn;
  }

  public boolean holdsThreshold(ICharmLearnArbitrator learnArbitrator) {
    int learnedCharms = 0;
    for (Charm charm : charms) {
      if (learnArbitrator.isLearned(charm)) {
        learnedCharms++;
      }
    }
    return learnedCharms >= template.getThreshold();
  }

  public String getLabel() {
    return template.getLabel() != null ? "Requirement." + template.getLabel() + "." + template.getThreshold() : null;
  }

  public ICharm[] getAllGroupCharms() {
    return charms.toArray(new ICharm[charms.size()]);
  }
}