package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import net.sf.anathema.character.main.magic.charm.prerequisite.AbstractGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;

public class IndirectGroupCharmLearnPrerequisite extends AbstractGroupCharmLearnPrerequisite implements IndirectCharmLearnPrerequisite {

	String keyword;
	
	public IndirectGroupCharmLearnPrerequisite(String keyword, String[] charms, int threshold) {
		super(charms, threshold);
	}

	@Override
	public String getStringLabel() {
		return "Requirement." + keyword + "." + getThreshold();
	}

}
