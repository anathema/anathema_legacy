package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.main.magic.basic.source.SourceBook;

public interface ISourceStringBuilder {
	  String createStringForSource(String magicId, SourceBook source);
}
