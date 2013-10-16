package net.sf.anathema.character.main.magic.charm.prerequisite;

import java.util.Map;

import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;

public interface CharmLearnPrerequisite {
	void link(Map<String, CharmImpl> charmsById);
	
	boolean isFulfilled(ICharmLearnArbitrator arbitrator);
}
