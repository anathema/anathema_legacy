package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class ArtifactStats extends AbstractStats implements IArtifactStats
{
	int attuneCost;
	
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
	
	@Override
	public IEquipmentStats[] getViews() {
	    IEquipmentStats[] views = new IEquipmentStats[4];
	    //views[0] = new ArtifactStatsDecorator(this, ArtifactAttuneType.Unattuned);
	    views[0] = new ArtifactStatsDecorator(this, ArtifactAttuneType.PartiallyAttuned);
	    views[1] = new ArtifactStatsDecorator(this, ArtifactAttuneType.ExpensivePartiallyAttuned);
	    views[2] = new ArtifactStatsDecorator(this, ArtifactAttuneType.FullyAttuned);
	    views[3] = new ArtifactStatsDecorator(this, ArtifactAttuneType.UnharmoniouslyAttuned);
	    return views;
	}

	  @Override
	  public String getId() {
	    return getName().getId();
	  }

}
