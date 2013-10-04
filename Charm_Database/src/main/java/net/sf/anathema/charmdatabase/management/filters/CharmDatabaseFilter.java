package net.sf.anathema.charmdatabase.management.filters;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface CharmDatabaseFilter {
	public boolean approvesCharm(Charm charm);
}
