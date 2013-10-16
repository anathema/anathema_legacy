package net.sf.anathema.character.main.magic.charm.prerequisite;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;

public interface DirectCharmLearnPrerequisite extends CharmLearnPrerequisite {
	Charm[] getDirectPredecessors();
	
	Charm[] getLearnPrerequisites(ICharmLearnArbitrator arbitrator);
}
