package net.sf.anathema.character.main.magic.charm.prerequisite;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;

public abstract class AbstractGroupCharmLearnPrerequisite implements MultipleCharmLearnPrerequisite {

	protected final Charm[] prerequisites;
	private final int threshold;
	
	public AbstractGroupCharmLearnPrerequisite(Charm[] charms, int threshold) {
		this.prerequisites = charms;
		this.threshold = threshold;
	}

	@Override
	public boolean isFulfilled(ICharmLearnArbitrator arbitrator) {
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
	public int getThreshold() {
		return threshold;
	}
}
