package net.sf.anathema.character.main.magic.charm.requirements;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.parser.charms.SelectiveCharmGroupTemplate;
import net.sf.anathema.lib.logging.Logger;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SelectiveCharmGroup {

  private final SelectiveCharmGroupTemplate template;
  private final List<CharmImpl> charms = new ArrayList<>();
  private final Logger logger = Logger.getLogger(SelectiveCharmGroup.class);

  public SelectiveCharmGroup(SelectiveCharmGroupTemplate template) {
    Preconditions.checkNotNull(template);
    this.template = template;
  }

  public void extractCharms(Map<String, ? extends CharmImpl> charmsById, CharmImpl child) {
    for (String charmId : template.getGroupCharmIds()) {
      CharmImpl groupCharm = charmsById.get(charmId);
      if (groupCharm == null) {
        logger.warn("Selective parent " + charmId + " not found for id " + charmId);
        continue;
      }
      charms.add(groupCharm);
      groupCharm.addChild(child);
    }
  }

  public Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    Set<Charm> prerequisiteCharms = new LinkedHashSet<>();
    List<CharmImpl> charmsToLearn = selectCharmsToLearn(learnArbitrator);
    for (CharmImpl learnCharm : charmsToLearn) {
      prerequisiteCharms.addAll(learnCharm.getLearnPrerequisitesCharms(learnArbitrator));
      prerequisiteCharms.add(learnCharm);
    }
    return prerequisiteCharms;
  }

  private List<CharmImpl> selectCharmsToLearn(ICharmLearnArbitrator learnArbitrator) {
    List<CharmImpl> charmsToLearn = new ArrayList<>();
    for (CharmImpl charm : charms) {
      if (charmsToLearn.size() >= template.getThreshold()) {
        return charmsToLearn;
      }
      if (learnArbitrator.isLearned(charm)) {
        charmsToLearn.add(charm);
      }
    }
    for (CharmImpl charm : charms) {
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
    for (CharmImpl charm : charms) {
      if (learnArbitrator.isLearned(charm)) {
        learnedCharms++;
      }
    }
    return learnedCharms >= template.getThreshold();
  }

  public String getLabel() {
    return template.getLabel() != null ? "Requirement." + template.getLabel() + "." + template.getThreshold() : null;
  }

  public Charm[] getAllGroupCharms() {
    return charms.toArray(new Charm[charms.size()]);
  }
}