package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class ArtifactStats extends AbstractStats implements IArtifactStats
{
	int attuneCost;
	boolean allowForeignAttunement;
	boolean requireAttunement;
	
	public Integer getAttuneCost()
	{
		return attuneCost;
	}
	
	public void setAttuneCost(int cost)
	{
		attuneCost = cost;
	}
	
	public ArtifactAttuneType getAttuneType()
	{
		return ArtifactAttuneType.FullyAttuned;
	}
	
	public boolean allowForeignAttunement()
	{
		return allowForeignAttunement;
	}
	
	public boolean requireAttunementToUse()
	{
		return requireAttunement;
	}
	
	public void setAllowForeignAttunement(boolean value)
	{
		allowForeignAttunement = value;
	}
	
	public void setRequireAttunement(boolean value)
	{
		requireAttunement = value;
	}
	
	@Override
	public IEquipmentStats[] getViews() {
	    IEquipmentStats[] views;
	    if (allowForeignAttunement())
	    {
	    	views = new IEquipmentStats[4];
	    	views[0] = new ArtifactStatsDecorator(this, ArtifactAttuneType.PartiallyAttuned, requireAttunement);
		views[1] = new ArtifactStatsDecorator(this, ArtifactAttuneType.ExpensivePartiallyAttuned, requireAttunement);
		views[2] = new ArtifactStatsDecorator(this, ArtifactAttuneType.FullyAttuned, requireAttunement);
		views[3] = new ArtifactStatsDecorator(this, ArtifactAttuneType.UnharmoniouslyAttuned, requireAttunement);
	    }
	    else
	    {
	    	views = new IEquipmentStats[1];
	    	views[0] = new ArtifactStatsDecorator(this, ArtifactAttuneType.FullyAttuned, requireAttunement);
	    }
	    
	    return views;
	}

	  @Override
	  public String getId() {
	    return getName().getId();
	  }

}
