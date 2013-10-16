package net.sf.anathema.charmdatabase.view.info;

import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.lib.util.Identifier;

public interface CharmSourcePanel {
	void setSources(SourceBook[] sources, Identifier type, String charmName);
}
