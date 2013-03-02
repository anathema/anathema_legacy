package net.sf.anathema.character.lunar.reporting.content.stats.knacks;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

public class KnackStats implements IKnackStats
{
	ICharm charm;
	
	public KnackStats(ICharm magic) {
		this.charm = magic;
	}

	@Override
	public String getGroupName(IResources resources) {
		return resources.getString(charm.getGroupId());
	}

	@Override
	public Identified getName() {
		return new Identifier(charm.getId());
	}

	@Override
	public String getNameString(IResources resources) {
		return resources.getString(charm.getId());
	}

	@Override
	public String getSourceString(IResources resources) {
		return resources.getString("ExaltedSourceBook." + charm.getPrimarySource().getId() + ".Short") +
			" p" +
			resources.getString(charm.getPrimarySource().getId() + "." + charm.getId() + ".Page");
	}

	@Override
	public String[] getDetailString(IResources resources) {
		return new String[] { "-" };
	}

}
