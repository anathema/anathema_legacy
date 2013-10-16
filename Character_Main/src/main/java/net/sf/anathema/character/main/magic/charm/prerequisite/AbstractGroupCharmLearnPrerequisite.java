package net.sf.anathema.character.main.magic.charm.prerequisite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;

public abstract class AbstractGroupCharmLearnPrerequisite implements MultipleCharmLearnPrerequisite {

	private final int threshold;
	private final String[] prerequisiteIds;
	protected Charm[] prerequisites;
	
	public AbstractGroupCharmLearnPrerequisite(String[] prerequisiteIds, int threshold) {
		this.prerequisiteIds = prerequisiteIds;
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
	
	@Override
	public void link(Map<String, CharmImpl> charmsById) {
		List<Charm> prerequisites = new ArrayList<>();
		for (String id : prerequisiteIds) {
			Charm parentCharm = charmsById.get(id);
			Preconditions.checkNotNull(parentCharm, "Parent Charm " + id + " not defined" );
			prerequisites.add(parentCharm);
		}
		this.prerequisites = prerequisites.toArray(new Charm[0]);
	}
}
