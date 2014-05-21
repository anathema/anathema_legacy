package net.sf.anathema.character.magic.charm.prerequisite;

import java.util.Map;

import net.sf.anathema.character.magic.charm.CharmImpl;
import net.sf.anathema.character.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.magic.charm.ICharmLearnableArbitrator;

public interface CharmLearnPrerequisite {
	void link(Map<String, CharmImpl> charmsById);
	
	boolean isSatisfied(ICharmLearnArbitrator arbitrator);
	
	boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator);
}
