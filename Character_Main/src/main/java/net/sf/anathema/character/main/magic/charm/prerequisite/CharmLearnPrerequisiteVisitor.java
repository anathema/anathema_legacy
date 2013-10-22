package net.sf.anathema.character.main.magic.charm.prerequisite;

import net.sf.anathema.character.main.magic.charm.prerequisite.impl.AttributeKnownCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.DirectGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.SimpleCharmLearnPrerequisite;

public interface CharmLearnPrerequisiteVisitor {
	void visitSimpleCharmPrerequisite(SimpleCharmLearnPrerequisite prerequisite);
	
	void visitDirectGroupCharmPrerequisite(DirectGroupCharmLearnPrerequisite prerequisite);
	
	void visitAttributeKnownCharmLearnPrerequisite(AttributeKnownCharmLearnPrerequisite prerequisite);
}
