package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.DirectCharmLearnPrerequisite;

public class SimpleCharmLearnPrerequisite implements DirectCharmLearnPrerequisite, CharmLearnPrerequisite {
	private final Charm prerequisite;
	
	public SimpleCharmLearnPrerequisite(Charm charm) {
		this.prerequisite = charm;
	}

	@Override
	public Charm[] getDirectPredecessors() {
		return new Charm[] { prerequisite };
	}

	@Override
	public boolean isFulfilled(ICharmLearnArbitrator arbitrator) {
		return arbitrator.isLearned(prerequisite);
	}
}
