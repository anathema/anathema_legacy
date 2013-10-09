package net.sf.anathema.charmdatabase.management.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;

public class GroupCharmPrerequisite implements CharmPrerequisite {
	private final Charm[] charms;
	private final int count;
	
	public GroupCharmPrerequisite(Charm[] charms, int count) {
		this.charms = charms;
		this.count = count;
	}

	@Override
	public String getText(MagicDisplayLabeler labeler) {
		List<String> charmNames = new ArrayList<String>();
		for (Charm charm : charms) {
			charmNames.add(labeler.getLabelForMagic(charm));
		}
		// TODO: We want a nice delimiter here, find a clean way to get the resource
		return Joiner.on(" ").join(charmNames);
	}
}
