package net.sf.anathema.character.generic.impl.magic.charm.special;

public class CharmTier
{
	int essenceRequirement = 0;
	int traitRequirement = 0;
	
	public CharmTier(int essence)
	{
		this(essence, 0);
	}
	
	public CharmTier(int essence, int trait)
	{
		this.essenceRequirement = essence;
		this.traitRequirement = trait;
	}
	
	public int getEssence()
	{
		return essenceRequirement;
	}
	
	public int getTrait()
	{
		return traitRequirement;
	}
}
