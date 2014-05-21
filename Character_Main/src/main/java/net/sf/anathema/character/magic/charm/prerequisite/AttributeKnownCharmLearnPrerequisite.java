package net.sf.anathema.character.magic.charm.prerequisite;

import net.sf.anathema.character.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.magic.charm.CharmImpl;
import net.sf.anathema.character.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.magic.charm.ICharmLearnableArbitrator;

import java.util.Map;

public class AttributeKnownCharmLearnPrerequisite implements IndirectCharmLearnPrerequisite {

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
	public String getStringLabel() {
		return "Requirement." + attribute.getId() + "." + count;
	}

	@Override
	public void link(Map<String, CharmImpl> charmsById) {
		// nothing to do
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AttributeKnownCharmLearnPrerequisite) {
			AttributeKnownCharmLearnPrerequisite prerequisite = (AttributeKnownCharmLearnPrerequisite) obj;
			return prerequisite.attribute.equals(attribute) && prerequisite.count == count;
		}
		return false;
	}
}
