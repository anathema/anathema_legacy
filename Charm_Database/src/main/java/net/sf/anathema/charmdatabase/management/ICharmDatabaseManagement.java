package net.sf.anathema.charmdatabase.management;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.charmdatabase.management.filters.CharmDatabaseFilter;
import net.sf.anathema.charmdatabase.management.model.ICharmEditModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;

public interface ICharmDatabaseManagement {

	ICharmEditModel getCharmEditModel();

	Identifier[] getCharmTypes();
	
	Identifier[] getGroupsForCharmType(Identifier type);
	
	Identifier[] getTraitsForCharmType(Identifier charmType);
	
	Charm[] getFilteredCharms();
	
	void addFilter(CharmDatabaseFilter filter);
	
	void removeFilter(CharmDatabaseFilter filter);
	
	void notifyFiltersUpdated();
	
	void addCharmListChangeListener(ChangeListener listener);
}
