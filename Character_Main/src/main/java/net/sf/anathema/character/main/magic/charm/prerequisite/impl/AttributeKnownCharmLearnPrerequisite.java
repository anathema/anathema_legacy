package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import java.util.Map;

import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.ICharmLearnableArbitrator;
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
	public boolean isSatisfied(ICharmLearnArbitrator arbitrator) {
		return arbitrator.hasLearnedThresholdCharmsWithKeyword(attribute, count);
	}
	
	@Override
	public boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator) {
		return false;
	}

	@Override
	public int getThreshold() {
		return count;
	}

	@Override
	public String getStringLabel() {
		return "Requirement." + attribute.getId() + "." + count;
	}

	@Override
	public void link(Map<String, CharmImpl> charmsById) {
		// nothing to do
	}
}
