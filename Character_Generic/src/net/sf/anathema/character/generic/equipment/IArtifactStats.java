package net.sf.anathema.character.generic.equipment;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IArtifactStats extends IEquipmentStats
{
	public Integer getAttuneCost();
	
	public ArtifactAttuneType getAttuneType();
	
	public IEquipmentStats[] getViews();
}
