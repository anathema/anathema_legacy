package net.sf.anathema.character.lunar.reporting.content.stats.knacks;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

public class KnackStats implements IKnackStats
{
	ICharm charm;
	
	public KnackStats(ICharm magic) {
		this.charm = magic;
	}

	@Override
	public String getGroupName(Resources resources) {
		return resources.getString(charm.getGroupId());
	}

	@Override
	public Identified getName() {
		return new Identifier(charm.getId());
	}

	@Override
	public String getNameString(Resources resources) {
		return resources.getString(charm.getId());
	}

	@Override
	public String getSourceString(Resources resources) {
		return resources.getString("ExaltedSourceBook." + charm.getPrimarySource().getId() + ".Short") +
			" p" +
			resources.getString(charm.getPrimarySource().getId() + "." + charm.getId() + ".Page");
	}

	@Override
	public String[] getDetailString(Resources resources) {
		return new String[] { "-" };
	}

}
