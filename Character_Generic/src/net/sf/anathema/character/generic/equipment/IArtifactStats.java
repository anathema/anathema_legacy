package net.sf.anathema.character.generic.equipment;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface IArtifactStats extends IEquipmentStats
{
	public Integer getAttuneCost();
	
	public boolean allowForeignAttunement();
	
	public boolean requireAttunementToUse();
	
	public ArtifactAttuneType getAttuneType();
	
	public IEquipmentStats[] getViews();
}
