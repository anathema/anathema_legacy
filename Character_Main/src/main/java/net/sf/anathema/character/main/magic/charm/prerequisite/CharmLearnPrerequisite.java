package net.sf.anathema.character.main.magic.charm.prerequisite;

import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;

public interface CharmLearnPrerequisite {
	boolean isFulfilled(ICharmLearnArbitrator arbitrator);
}
