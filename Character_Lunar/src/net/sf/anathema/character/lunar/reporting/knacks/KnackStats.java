package net.sf.anathema.character.lunar.reporting.knacks;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

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
	public IIdentificate getName() {
		return new Identificate(charm.getId());
	}

	@Override
	public String getNameString(IResources resources) {
		return resources.getString(charm.getId());
	}

	@Override
	public String getSourceString(IResources resources) {
		return resources.getString("ExaltedSourceBook." + charm.getSource() + ".Short") +
			" p" +
			resources.getString(charm.getSource().getId() + "." + charm.getId() + ".Page");
	}

	@Override
	public String[] getDetailString(IResources resources) {
		return new String[] { "-" };
	}

}
