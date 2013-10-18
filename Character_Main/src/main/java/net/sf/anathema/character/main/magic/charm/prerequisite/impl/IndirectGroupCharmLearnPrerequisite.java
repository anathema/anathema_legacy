package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import java.util.Arrays;

import net.sf.anathema.character.main.magic.charm.ICharmLearnableArbitrator;
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

	@Override
	public boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator) {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IndirectGroupCharmLearnPrerequisite) {
			IndirectGroupCharmLearnPrerequisite prerequisite = (IndirectGroupCharmLearnPrerequisite) obj;
			return Arrays.deepEquals(prerequisites, prerequisite.prerequisites) && prerequisite.getThreshold() == getThreshold() &&
				   getStringLabel().equals(prerequisite.getStringLabel());
		}
		return false;
	}

}
