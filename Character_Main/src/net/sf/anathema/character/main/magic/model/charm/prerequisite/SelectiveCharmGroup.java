package net.sf.anathema.character.main.magic.model.charm.prerequisite;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;
import net.sf.anathema.lib.logging.Logger;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SelectiveCharmGroup {

  private final SelectiveCharmGroupTemplate template;
  private final List<Charm> charms = new ArrayList<>();
  private final Logger logger = Logger.getLogger(SelectiveCharmGroup.class);

  public SelectiveCharmGroup(SelectiveCharmGroupTemplate template) {
    Preconditions.checkNotNull(template);
    this.template = template;
  }

  public void extractCharms(Map<String, ? extends Charm> charmsById, Charm child) {
    for (String charmId : template.getGroupCharmIds()) {
      Charm groupCharm = charmsById.get(charmId);
      if (groupCharm == null) {
        logger.warn("Selective parent " + charmId + " not found for id " + charmId);
        continue;
      }
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