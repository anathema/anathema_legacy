package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.IBasicCharacterData;

public interface IUpgradableCharm extends IMultipleEffectCharm
{
	int getUpgradeBPCost(IBasicCharacterData data);
	
	int getUpgradeXPCost(IBasicCharacterData data);
	
	boolean requiresBase();
}
