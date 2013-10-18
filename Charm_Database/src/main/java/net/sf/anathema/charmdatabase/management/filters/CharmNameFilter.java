package net.sf.anathema.charmdatabase.management.filters;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;

public class CharmNameFilter implements CharmDatabaseFilter {

	private final MagicDisplayLabeler labeler;
	String currentText = "";
	
	public CharmNameFilter(MagicDisplayLabeler labeler) {
		this.labeler = labeler;
	}
	
	public void setCurrentText(String text) {
		this.currentText = text.toLowerCase();
	}
	
	@Override
	public boolean approvesCharm(Charm charm) {
		return labeler.getLabelForMagic(charm).toLowerCase().contains(currentText);
	}

}
