package net.sf.anathema.character.magic.charm.prerequisite;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.ICharmLearnArbitrator;

public interface DirectCharmLearnPrerequisite extends CharmLearnPrerequisite {
	Charm[] getDirectPredecessors();
	
	Charm[] getLearnPrerequisites(ICharmLearnArbitrator arbitrator);
}
