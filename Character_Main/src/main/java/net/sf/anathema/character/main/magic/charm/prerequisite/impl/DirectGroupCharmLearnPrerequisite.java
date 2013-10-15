package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.AbstractGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.DirectCharmLearnPrerequisite;

public class DirectGroupCharmLearnPrerequisite extends AbstractGroupCharmLearnPrerequisite
	implements DirectCharmLearnPrerequisite {

	public DirectGroupCharmLearnPrerequisite(Charm[] charms, int threshold) {
		super(charms, threshold);
	}

	@Override
	public Charm[] getDirectPredecessors() {
		return prerequisites;
	}
}
