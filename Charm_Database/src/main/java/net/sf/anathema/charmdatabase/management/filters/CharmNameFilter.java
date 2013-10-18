package net.sf.anathema.charmdatabase.management.filters;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.framework.environment.Resources;

public class CharmNameFilter implements CharmDatabaseFilter {

	private final Resources resources;
	String currentText = "";
	
	public CharmNameFilter(Resources resources) {
		this.resources = resources;
	}
	
	public void setCurrentText(String text) {
		this.currentText = text.toLowerCase();
	}
	
	@Override
	public boolean approvesCharm(Charm charm) {
		return resources.getString(charm.getId()).toLowerCase().contains(currentText);
	}

}
