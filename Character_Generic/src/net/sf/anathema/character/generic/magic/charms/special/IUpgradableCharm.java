package net.sf.anathema.character.generic.magic.charms.special;

public interface IUpgradableCharm extends IMultipleEffectCharm
{
	public int getUpgradeBPCost();
	
	public int getUpgradeXPCost();
	
	public boolean requiresBase();
}
