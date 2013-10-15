package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.MultipleCharmLearnPrerequisite;

public class AttributeKnownCharmLearnPrerequisite implements IndirectCharmLearnPrerequisite, MultipleCharmLearnPrerequisite {

	private final MagicAttribute attribute;
	private final int count;
	
	public AttributeKnownCharmLearnPrerequisite(MagicAttribute attribute, int count) {
		this.attribute = attribute;
		this.count = count;
	}
	
	@Override
	public boolean isFulfilled(ICharmLearnArbitrator arbitrator) {
		// TODO need a way to determine if this can be fulfilled
		return false;
	}

	@Override
	public int getThreshold() {
		return count;
	}

	@Override
	public String getRequirementLabel() {
		return "Requirement." + attribute.getId() + "." + count;
	}

}
