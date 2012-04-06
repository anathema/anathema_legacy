package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.IBasicCharacterData;

public interface IUpgradableCharm extends IMultipleEffectCharm
{
	public int getUpgradeBPCost(IBasicCharacterData data);
	
	public int getUpgradeXPCost(IBasicCharacterData data);
	
	public boolean requiresBase();
}
