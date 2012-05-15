package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.traits.INamedGenericTrait;

public interface IEquipmentStatsOption
{
	String getName();
	
	String getType();
	
	// Options only apply to weapon stats for now
	
	int getAccuracyModifier();
	
	int getDefenseModifier();
	
	INamedGenericTrait getUnderlyingTrait();
}
