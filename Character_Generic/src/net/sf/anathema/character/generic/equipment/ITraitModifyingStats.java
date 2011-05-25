package net.sf.anathema.character.generic.equipment;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface ITraitModifyingStats extends IEquipmentStats
{
	public Integer getDDVMod();
	public Integer getPDVMod();
	public Integer getMDDVMod();
	public Integer getMPDVMod();
	
	public Integer getMeleeSpeedMod();
	public Integer getMeleeAccuracyMod();
	public Integer getMeleeDamageMod();
	public Integer getMeleeRateMod();
	
	public Integer getRangedSpeedMod();
	public Integer getRangedAccuracyMod();
	public Integer getRangedDamageMod();
	public Integer getRangedRateMod();
	
	public Integer getJoinBattleMod();
	public Integer getJoinDebateMod();
	public Integer getJoinWarMod();
}
