package net.sf.anathema.character.main.magic.charm.prerequisite;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface DirectCharmLearnPrerequisite {
	Charm[] getDirectPredecessors();
}
