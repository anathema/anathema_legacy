package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;

public interface IEquipmentStatsOption
{
	public String getName();
	
	public String getType();
	
	// Options only apply to weapon stats for now
	
	public int getAccuracyModifier();
	
	public int getDefenseModifier();
	
	public INamedGenericTrait getUnderlyingTrait();
}
