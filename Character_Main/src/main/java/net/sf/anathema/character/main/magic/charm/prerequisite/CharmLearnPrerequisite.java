package net.sf.anathema.character.main.magic.charm.prerequisite;

import java.util.Map;

import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.ICharmLearnableArbitrator;

public interface CharmLearnPrerequisite {
<<<<<<< HEAD
	void link(Map<String, CharmImpl> charmsById);
	
	boolean isSatisfied(ICharmLearnArbitrator arbitrator);
	
	boolean isAutoSatisfiable(ICharmLearnableArbitrator arbitrator);
=======
	boolean isFulfilled(ICharmLearnArbitrator arbitrator);
	
	void visitCharmLearnPrerequisite(CharmLearnPrerequisiteVisitor visitor);
>>>>>>> 3168c881528cb8effb47159421c1a4b3eb21b50c
}
