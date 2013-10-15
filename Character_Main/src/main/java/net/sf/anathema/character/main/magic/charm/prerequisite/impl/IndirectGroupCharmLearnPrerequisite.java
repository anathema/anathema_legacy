package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.AbstractGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;

public class IndirectGroupCharmLearnPrerequisite extends AbstractGroupCharmLearnPrerequisite implements IndirectCharmLearnPrerequisite {

	String keyword;
	
	public IndirectGroupCharmLearnPrerequisite(String keyword, Charm[] charms, int threshold) {
		super(charms, threshold);
	}

	@Override
	public String getRequirementLabel() {
		return "Requirement." + keyword + "." + getThreshold();
	}

}
