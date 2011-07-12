package net.sf.anathema.character.generic.equipment;

public interface IEquipmentModifiers
{
	public int getMobilityPenalty();
	
	public int getDDVMod();
	public int getPDVMod();
	public int getMDDVMod();
	public int getMPDVMod();
	
	public int getMeleeSpeedMod();
	public int getMeleeAccuracyMod();
	public int getMeleeDamageMod();
	public int getMeleeRateMod();
	
	public int getRangedSpeedMod();
	public int getRangedAccuracyMod();
	public int getRangedDamageMod();
	public int getRangedRateMod();
	
	public int getJoinBattleMod();
	public int getJoinDebateMod();
	public int getJoinWarMod();
}
