package net.sf.anathema.character.main.magic.charm.prerequisite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.SimpleCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.parser.charms.CharmPrerequisiteList;

public class CharmLearnPrerequisiteBuilder {
	private final CharmPrerequisiteList prerequisiteList;
	private final Charm[] parents;
	
	public CharmLearnPrerequisiteBuilder(CharmPrerequisiteList list, Charm[] parents) {
		this.prerequisiteList = list;
		this.parents = parents;
	}
	
	public CharmLearnPrerequisite[] getPrerequisites() {
		// TODO: Most of this code is temporary, translating the various already
		// existing forms of prerequisites in to a new paradigm. Next step is
		// to substitute the places the originals are currently built with the new classes.
		List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
		
		for (Charm charm : parents) {
			prerequisites.add(new SimpleCharmLearnPrerequisite(charm));
		}
		
		prerequisites.addAll(Arrays.asList(prerequisiteList.getLearnPrerequisites()));
		
		return prerequisites.toArray(new CharmLearnPrerequisite[0]);
	}
}
