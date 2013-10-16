package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.lib.util.Identifier;

public class CharmInformationUtilities {
	public static String getFormalCharmName(Identifier type, String textualName) {
		return type.getId() + "." + textualName.replaceAll(" ", "");
	}
}
