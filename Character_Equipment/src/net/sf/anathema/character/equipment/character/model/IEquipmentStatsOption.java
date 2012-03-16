package net.sf.anathema.character.equipment.character.model;

public interface IEquipmentStatsOption
{
	public String getName();
	
	public String getType();
	
	// Options only apply to weapon stats for now
	
	public int getAccuracyModifier();
	
	public int getDefenseModifier();
}
