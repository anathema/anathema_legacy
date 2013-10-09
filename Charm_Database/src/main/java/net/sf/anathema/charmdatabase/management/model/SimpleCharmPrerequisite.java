package net.sf.anathema.charmdatabase.management.model;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;

public class SimpleCharmPrerequisite implements CharmPrerequisite {
	private final Charm charm;
	
	public SimpleCharmPrerequisite(Charm charm) {
		this.charm = charm;
	}

	@Override
	public String getText(MagicDisplayLabeler labeler) {
		return labeler.getLabelForMagic(charm);
	}
	
}
