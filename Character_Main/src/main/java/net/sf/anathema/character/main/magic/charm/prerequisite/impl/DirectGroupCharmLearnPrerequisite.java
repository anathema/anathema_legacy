package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.prerequisite.AbstractGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.DirectCharmLearnPrerequisite;

public class DirectGroupCharmLearnPrerequisite extends AbstractGroupCharmLearnPrerequisite
	implements DirectCharmLearnPrerequisite {

	public DirectGroupCharmLearnPrerequisite(String[] charms, int threshold) {
		super(charms, threshold);
	}

	@Override
	public Charm[] getDirectPredecessors() {
		return prerequisites;
	}

	public Charm[] getLearnPrerequisites(ICharmLearnArbitrator learnArbitrator) {
		Set<Charm> prerequisiteCharms = new LinkedHashSet<>();
		List<Charm> charmsToLearn = selectCharmsToLearn(learnArbitrator);
		for (Charm learnCharm : charmsToLearn) {
			prerequisiteCharms.addAll(learnCharm.getLearnPrerequisitesCharms(learnArbitrator));
			prerequisiteCharms.add(learnCharm);
		}
		return prerequisiteCharms.toArray(new Charm[0]);
	}

	private List<Charm> selectCharmsToLearn(ICharmLearnArbitrator learnArbitrator) {
		List<Charm> charmsToLearn = new ArrayList<>();
		for (Charm charm : getDirectPredecessors()) {
			if (charmsToLearn.size() >= getThreshold()) {
				return charmsToLearn;
			}
			if (learnArbitrator.isLearned(charm)) {
				charmsToLearn.add(charm);
			}
		}
		for (Charm charm : getDirectPredecessors()) {
			if (charmsToLearn.size() >= getThreshold()) {
				return charmsToLearn;
			}
			if (!learnArbitrator.isLearned(charm)) {
				charmsToLearn.add(charm);
			}
		}
		return charmsToLearn;
	}
}
