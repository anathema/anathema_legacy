package net.sf.anathema.character.generic.impl.magic.charm.prerequisite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;
import net.sf.anathema.lib.collection.ListOrderedSet;

public class SelectiveCharmGroup {

  private final SelectiveCharmGroupTemplate template;
  private final List<Charm> charms = new ArrayList<Charm>();

  public SelectiveCharmGroup(SelectiveCharmGroupTemplate template) {
    Ensure.ensureNotNull(template);
    this.template = template;
  }

  public void extractCharms(Map<String, ? extends Charm> charmsById, Charm child) {
    for (String charmId : template.getGroupCharmIds()) {
      Charm groupCharm = charmsById.get(charmId);
      Ensure.ensureNotNull("Charm not found for id " + charmId, groupCharm); //$NON-NLS-1$
      charms.add(groupCharm);
      groupCharm.addChild(child);
    }
  }

  public Set<ICharm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator) {
    Set<ICharm> prerequisiteCharms = new ListOrderedSet<ICharm>();
    List<Charm> charmsToLearn = selectCharmsToLearn(learnArbitrator);
    for (Charm learnCharm : charmsToLearn) {
      prerequisiteCharms.addAll(learnCharm.getLearnPrerequisitesCharms(learnArbitrator));
      prerequisiteCharms.add(learnCharm);
    }
    return prerequisiteCharms;
  }

  private List<Charm> selectCharmsToLearn(ICharmLearnArbitrator learnArbitrator) {
    List<Charm> charmsToLearn = new ArrayList<Charm>();
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
  
  public String getLabel()
  {
	  return "Requirement." + template.getLabel() + "." + template.getThreshold();
  }

  public ICharm[] getAllGroupCharms() {
    return charms.toArray(new ICharm[charms.size()]);
  }
}